package com.minacloud.file.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.file.convertor.FileUploadInfoCOConvertor;
import com.minacloud.file.dto.clientobject.FileUploadInfoCO;
import com.minacloud.file.dto.cmd.SaveFileUploadInfoCmd;
import com.minacloud.file.gateway.FileUploadInfoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveFileUploadInfoCmdExe implements BaseCmdExecutor<SaveFileUploadInfoCmd, Response> {
    private final FileUploadInfoGateway fileUploadInfoGateway;
    private final FileUploadInfoCOConvertor fileUploadInfoCOConvertor;

    @Override
    public Response execute(SaveFileUploadInfoCmd cmd) {
        FileUploadInfoCO fileUploadInfo = cmd.getFileUploadInfo();
        this.fileUploadInfoGateway.create(fileUploadInfoCOConvertor.toTarget(fileUploadInfo));
        return SingleResponse.buildSuccess();
    }
}
