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
package com.minacloud.file.config;

import com.google.common.collect.Multimap;
import io.minio.CreateMultipartUploadResponse;
import io.minio.ListPartsResponse;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Part;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class MinacloudUploadClient extends MinioClient {
    protected MinacloudUploadClient(MinioClient client) {
        super(client);
    }

    /**
     * 获取 uploadId
     *
     * @param bucketName       bucketName
     * @param region           region
     * @param objectName       objectName
     * @param headers          headers
     * @param extraQueryParams extraQueryParams
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws XmlParserException
     * @throws InvalidResponseException
     * @throws InternalException
     */
    public String getUploadId(String bucketName, String region, String objectName,
                              Multimap<String, String> headers, Multimap<String, String> extraQueryParams)
            throws Exception {
        CreateMultipartUploadResponse response = this.createMultipartUpload(bucketName, region, objectName, headers, extraQueryParams);

        return response.result().uploadId();
    }

    /**
     * 合并分片
     *
     * @param bucketName
     * @param region
     * @param objectName
     * @param uploadId
     * @param parts
     * @param extraHeaders
     * @param extraQueryParams
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws XmlParserException
     * @throws InvalidResponseException
     * @throws InternalException
     */
    public ObjectWriteResponse mergeMultipart(String bucketName, String region, String objectName, String uploadId,
                                              Part[] parts, Multimap<String, String> extraHeaders, Multimap<String, String> extraQueryParams) throws Exception {
        return this.completeMultipartUpload(bucketName, region, objectName, uploadId, parts, extraHeaders, extraQueryParams);
    }

    /**
     * 查询分分片列表
     *
     * @param bucketName
     * @param region
     * @param objectName
     * @param maxParts
     * @param partNumberMaker
     * @param uploadId
     * @param extraHeaders
     * @param extraQueryParams
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws XmlParserException
     * @throws InvalidResponseException
     * @throws InternalException
     */
    public ListPartsResponse listMultipart(String bucketName, String region, String objectName, Integer maxParts, Integer partNumberMaker,
                                           String uploadId, Multimap<String, String> extraHeaders, Multimap<String, String> extraQueryParams) throws Exception {
        return this.listParts(bucketName, region, objectName, maxParts, partNumberMaker, uploadId, extraHeaders, extraQueryParams);
    }
}
