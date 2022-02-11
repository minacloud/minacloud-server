package com.minacloud.file.command;

import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.file.convertor.FileUploadInfoCOConvertor;
import com.minacloud.file.domain.FileUploadInfo;
import com.minacloud.file.dto.clientobject.FileUploadInfoCO;
import com.minacloud.file.gateway.FileUploadInfoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileUploadInfoSearchCmdExe implements BaseCmdExecutor<SingleParamQry, SingleResponse> {
    private final FileUploadInfoGateway fileUploadInfoGateway;
    private final FileUploadInfoCOConvertor fileUploadInfoCOConvertor;

    @Override
    public SingleResponse<FileUploadInfoCO> execute(SingleParamQry cmd) {
        FileUploadInfo uploadInfo = fileUploadInfoGateway.findByFileMd5(cmd.getParam());
        return SingleResponse.of(fileUploadInfoCOConvertor.toSource(uploadInfo));
    }
}
