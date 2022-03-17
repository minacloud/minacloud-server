/*
 * minacloud-file-app - minacloud
 * Copyright © 2021 minacloud (lslvxy@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.minacloud.file.helper;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.cola.exception.BizException;
import com.google.common.collect.HashMultimap;
import com.minacloud.file.config.MinacloudUploadClient;
import com.minacloud.file.config.MinioPropertiesConfig;
import com.minacloud.file.dto.FileShardingResult;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListPartsResponse;
import io.minio.ObjectWriteResponse;
import io.minio.http.Method;
import io.minio.messages.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Component
@Slf4j
@RequiredArgsConstructor
public class MinioHelper {
    private final MinioPropertiesConfig minioPropertiesConfig;
    private final MinacloudUploadClient customMinioClient;

    /**
     * 初始化获取 uploadId
     *
     * @param objectName  文件名
     * @param partCount   分片总数
     * @param contentType contentType
     * @return
     */
    public FileShardingResult initMultiPartUpload(String objectName, int partCount, String contentType) {
        HashMultimap<String, String> headers = HashMultimap.create();
        headers.put("Content-Type", contentType);

        String uploadId = "";
        Map<Integer, String> partUrlMap = new HashMap<>();
        try {
            // 获取 uploadId
            uploadId = customMinioClient.getUploadId(minioPropertiesConfig.getBucketName(), null, objectName, headers, null);
            Map<String, String> paramsMap = new HashMap<>(2);
            paramsMap.put("uploadId", uploadId);
            for (int i = 1; i <= partCount; i++) {
                paramsMap.put("partNumber", String.valueOf(i));
                // 获取上传 url
                String uploadUrl = customMinioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                        // 注意此处指定请求方法为 PUT，前端需对应，否则会报 `SignatureDoesNotMatch` 错误
                        .method(Method.PUT)
                        .bucket(minioPropertiesConfig.getBucketName())
                        .object(objectName)
                        // 指定上传连接有效期
                        .expiry(minioPropertiesConfig.getChunkUploadExpirySecond(), TimeUnit.SECONDS)
                        .extraQueryParams(paramsMap).build());

                partUrlMap.put(i, uploadUrl);
            }
        } catch (Exception e) {
            log.error("initMultiPartUpload Error:" + e);
            return null;
        }
        // 过期时间
        DateTime expireTime = DateUtil.offset(new Date(), DateField.SECOND, minioPropertiesConfig.getChunkUploadExpirySecond());
        FileShardingResult result = new FileShardingResult();
        result.setUploadId(uploadId);
        result.setExpiryTime(expireTime.toJdkDate());
        result.setUploadUrls(partUrlMap);
        return result;
    }

    /**
     * 分片合并
     *
     * @param objectName 文件名
     * @param uploadId   uploadId
     * @return
     */
    public String mergeMultiPartUpload(String objectName, String uploadId) {
        // todo 最大1000分片 这里好像可以改吧
        Part[] parts = new Part[1000];
        int partIndex = 0;
        ListPartsResponse partsResponse = listUploadPartsBase(objectName, uploadId);
        if (null == partsResponse) {
            log.error("查询文件分片列表为空");
            throw new BizException("分片列表为空");
        }
        for (Part partItem : partsResponse.result().partList()) {
            parts[partIndex] = new Part(partIndex + 1, partItem.etag());
            partIndex++;
        }
        ObjectWriteResponse objectWriteResponse;
        try {
            objectWriteResponse = customMinioClient.mergeMultipart(minioPropertiesConfig.getBucketName(), null, objectName, uploadId, parts, null, null);
        } catch (Exception e) {
            log.error("分片合并失败：" + e);
            throw new BizException("分片合并失败：" + e.getMessage());
        }
        if (null == objectWriteResponse) {
            log.error("合并失败，合并结果为空");
            throw new BizException("分片合并失败");
        }
        return objectWriteResponse.region();
    }

    /**
     * 获取已上传的分片列表
     *
     * @param objectName 文件名
     * @param uploadId   uploadId
     * @return
     */
    public List<Integer> listUploadChunkList(String objectName, String uploadId) {
        ListPartsResponse partsResponse = listUploadPartsBase(objectName, uploadId);
        if (null == partsResponse) {
            return Collections.emptyList();
        }
        return partsResponse.result().partList().stream().map(Part::partNumber).collect(Collectors.toList());
    }

    private ListPartsResponse listUploadPartsBase(String objectName, String uploadId) {
        int maxParts = 1000;
        ListPartsResponse partsResponse;
        try {
            partsResponse = customMinioClient.listMultipart(minioPropertiesConfig.getBucketName(), null, objectName, maxParts, 0, uploadId, null, null);
        } catch (Exception e) {
            log.error("查询文件分片列表错误：{}，uploadId:{}", e, uploadId);
            return null;
        }
        return partsResponse;
    }
}
