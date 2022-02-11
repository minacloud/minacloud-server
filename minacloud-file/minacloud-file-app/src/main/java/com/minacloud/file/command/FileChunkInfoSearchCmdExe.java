package com.minacloud.file.command;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.MultiResponse;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.file.convertor.FileChunkInfoCOConvertor;
import com.minacloud.file.domain.FileChunkInfo;
import com.minacloud.file.dto.clientobject.FileChunkInfoCO;
import com.minacloud.file.dto.qry.FileChunkInfoQry;
import com.minacloud.file.gateway.FileChunkInfoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileChunkInfoSearchCmdExe implements BaseCmdExecutor<FileChunkInfoQry, MultiResponse<FileChunkInfoCO>> {
    private final FileChunkInfoGateway fileChunkInfoGateway;
    private final FileChunkInfoCOConvertor fileChunkInfoCOConvertor;

    @Override
    public MultiResponse<FileChunkInfoCO> execute(FileChunkInfoQry cmd) {
        String fileMd5 = cmd.getFileMd5();
        String uploadId = cmd.getUploadId();
        List<FileChunkInfo> list = new ArrayList<>();
        if (StrUtil.isNotBlank(fileMd5) && StrUtil.isNotBlank(uploadId)) {
            list = fileChunkInfoGateway.findByFileMd5AndUploadId(fileMd5, uploadId);
        } else if (StrUtil.isNotBlank(fileMd5)) {
            list = fileChunkInfoGateway.findByFileMd5(fileMd5);
        }

        List<FileChunkInfoCO> fileChunkInfoCOS = fileChunkInfoCOConvertor.toSource(list);
        return MultiResponse.of(fileChunkInfoCOS);
    }
}
