package com.minacloud.file.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.file.convertor.FileUploadInfoCOConvertor;
import com.minacloud.file.domain.FileUploadInfo;
import com.minacloud.file.dto.clientobject.FileUploadInfoCO;
import com.minacloud.file.dto.cmd.SaveFileUploadInfoCmd;
import com.minacloud.file.gateway.FileUploadInfoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStatusByMd5CmdExe implements BaseCmdExecutor<SaveFileUploadInfoCmd, Response> {
    private final FileUploadInfoGateway fileUploadInfoGateway;
    private final FileUploadInfoCOConvertor fileUploadInfoCOConvertor;

    @Override
    public Response execute(SaveFileUploadInfoCmd cmd) {
        FileUploadInfoCO fileUploadInfo = cmd.getFileUploadInfo();
        FileUploadInfo minioFileUploadInfo = this.fileUploadInfoGateway.findByFileMd5(fileUploadInfo.getFileMd5());
        if (null == minioFileUploadInfo) {
            throw new BizException("RECORD_NOT_EXISTED");
        }
        minioFileUploadInfo.setFileStatus(fileUploadInfo.getFileStatus());
        minioFileUploadInfo.setFileUrl(fileUploadInfo.getFileUrl());
        this.fileUploadInfoGateway.update(fileUploadInfoCOConvertor.toTarget(fileUploadInfo));
        return SingleResponse.buildSuccess();
    }
}
