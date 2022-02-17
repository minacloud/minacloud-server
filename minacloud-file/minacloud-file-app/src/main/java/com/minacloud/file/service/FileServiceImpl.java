package com.minacloud.file.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.file.api.FileChunkInfoService;
import com.minacloud.file.api.FileService;
import com.minacloud.file.api.FileUploadInfoService;
import com.minacloud.file.dto.FileOperationResult;
import com.minacloud.file.dto.FileShardingResult;
import com.minacloud.file.dto.clientobject.FileChunkInfoCO;
import com.minacloud.file.dto.clientobject.FileUploadInfoCO;
import com.minacloud.file.dto.cmd.MergeMultipartCmd;
import com.minacloud.file.dto.cmd.SaveFileChunkInfoCmd;
import com.minacloud.file.dto.cmd.SaveFileUploadInfoCmd;
import com.minacloud.file.dto.cmd.UploadFileCmd;
import com.minacloud.file.dto.qry.FileChunkInfoQry;
import com.minacloud.file.dto.qry.ListUploadPartsQry;
import com.minacloud.file.emuns.FileStatusEnum;
import com.minacloud.file.helper.MinioHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lanweihong
 * @date 2022/1/4 16:24
 */
@Service
@Slf4j
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final MinioHelper minioHelper;
    private final FileUploadInfoService fileUploadInfoService;
    private final FileChunkInfoService fileChunkInfoService;


    @Override
    public SingleResponse<FileShardingResult> getUploadId(UploadFileCmd param) {
        FileShardingResult fileShardingResult;
        SingleResponse<FileUploadInfoCO> minioFileUploadInfo = this.fileUploadInfoService.getByFileMd5(SingleParamQry.of(param.getFileMd5()));
        if (ObjectUtil.isNull(minioFileUploadInfo.getData())) {
            // 计算分片数量
            double partCount = Math.ceil(param.getFileSize() / param.getChunkSize());
            log.info("总分片数：" + partCount);
            fileShardingResult = minioHelper.initMultiPartUpload(param.getFileName(), (int) partCount, param.getContentType());
            if (null != fileShardingResult) {
                FileUploadInfoCO saveParam = new FileUploadInfoCO();
                saveParam.setUploadId(fileShardingResult.getUploadId());
                saveParam.setFileMd5(param.getFileMd5());
                saveParam.setFileName(param.getFileName());
                saveParam.setTotalChunk((int) partCount);
                saveParam.setFileUrl("");
                saveParam.setFileStatus(FileStatusEnum.UN_UPLOADED.getCode());
                // 保存文件上传信息
                SaveFileUploadInfoCmd cmd = new SaveFileUploadInfoCmd(saveParam);
                fileUploadInfoService.createFileUploadInfo(cmd);

                FileChunkInfoCO chunkUploadInfoParam = new FileChunkInfoCO();
                chunkUploadInfoParam.setUploadUrls(fileShardingResult.getUploadUrls());
                chunkUploadInfoParam.setUploadId(fileShardingResult.getUploadId());
                chunkUploadInfoParam.setExpiryTime(fileShardingResult.getExpiryTime());
                chunkUploadInfoParam.setFileMd5(param.getFileMd5());
                chunkUploadInfoParam.setFileName(param.getFileName());
                // 保存分片上传信息
                SaveFileChunkInfoCmd ccmd = new SaveFileChunkInfoCmd(chunkUploadInfoParam);
                fileChunkInfoService.saveFileChunkInfo(ccmd);
            }
            return SingleResponse.of(fileShardingResult);
        }
        // 查询分片上传地址
        MultiResponse<FileChunkInfoCO> list = fileChunkInfoService.findByFileMd5AndUploadId(new FileChunkInfoQry(minioFileUploadInfo.getData().getFileMd5(), minioFileUploadInfo.getData().getUploadId()));
        Map<Integer, String> uploadUrlList = list.getData().stream().collect(Collectors.toMap(FileChunkInfoCO::getChunkNumber, FileChunkInfoCO::getChunkUploadUrl, (a, b) -> b));
        fileShardingResult = new FileShardingResult();
        fileShardingResult.setUploadUrls(uploadUrlList);
        fileShardingResult.setUploadId(minioFileUploadInfo.getData().getUploadId());
        return SingleResponse.of(fileShardingResult);
    }


    @Override
    public MultiResponse<Integer> listUploadParts(ListUploadPartsQry qry) {
        List<Integer> fileIds = minioHelper.listUploadChunkList(qry.getObjectName(), qry.getUploadId());
        return MultiResponse.of(fileIds);
    }

    @Override
    public SingleResponse<FileOperationResult> fileExists(SingleParamQry md5) {
        FileOperationResult result = new FileOperationResult();
        SingleResponse<FileUploadInfoCO> minioFileUploadInfo = this.fileUploadInfoService.getByFileMd5(md5);
        if (!minioFileUploadInfo.isSuccess()) {
            result.setStatus(FileStatusEnum.UN_UPLOADED.getCode());
            return SingleResponse.of(result);
        }
        // 已上传
        FileUploadInfoCO data = minioFileUploadInfo.getData();
        if (data.getFileStatus().equals(FileStatusEnum.UPLOADED.getCode())) {
            result.setStatus(FileStatusEnum.UPLOADED.getCode());
            result.setUrl(data.getFileUrl());
            return SingleResponse.of(result);
        }
        // 查询已上传分片列表并返回已上传列表
        ListUploadPartsQry qry = new ListUploadPartsQry();
        qry.setObjectName(data.getFileName());
        qry.setUploadId(data.getUploadId());
        MultiResponse<Integer> chunkUploadedList = listUploadParts(qry);
        result.setStatus(FileStatusEnum.UPLOADING.getCode());
        result.setChunkUploadedList(chunkUploadedList.getData());
        return SingleResponse.of(result);
    }


    @Override
    public Response mergeMultipartUpload(MergeMultipartCmd param) {
        String result = minioHelper.mergeMultiPartUpload(param.getFileName(), param.getUploadId());
        if (StrUtil.isNotBlank(result)) {
            FileUploadInfoCO fileUploadInfoCO = new FileUploadInfoCO();
            fileUploadInfoCO.setFileUrl(result);
            fileUploadInfoCO.setFileMd5(param.getMd5());
            fileUploadInfoCO.setFileStatus(FileStatusEnum.UPLOADED.getCode());

            // 更新状态
            SaveFileUploadInfoCmd cmd = new SaveFileUploadInfoCmd();
            cmd.setFileUploadInfo(fileUploadInfoCO);
            fileUploadInfoService.updateFileStatusByFileMd5(cmd);
        }
        return Response.buildSuccess();
    }
}
