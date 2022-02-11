package com.minacloud.file.command;

import com.alibaba.cola.dto.Response;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.file.domain.FileChunkInfo;
import com.minacloud.file.dto.clientobject.FileChunkInfoCO;
import com.minacloud.file.dto.cmd.SaveFileChunkInfoCmd;
import com.minacloud.file.gateway.FileChunkInfoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveFileChunkInfoCmdExe implements BaseCmdExecutor<SaveFileChunkInfoCmd, Response> {
    private final FileChunkInfoGateway fileChunkInfoGateway;

    @Override
    public Response execute(SaveFileChunkInfoCmd cmd) {
        FileChunkInfoCO fileChunkInfo = cmd.getFileChunkInfo();

        List<FileChunkInfo> list = new ArrayList<>();
        fileChunkInfo.getUploadUrls().forEach((k, v) -> {
            FileChunkInfo tempObj = new FileChunkInfo();
            tempObj.setChunkNumber(k);
            tempObj.setFileMd5(fileChunkInfo.getFileMd5());
            tempObj.setUploadId(fileChunkInfo.getUploadId());
            tempObj.setExpiryTime(fileChunkInfo.getExpiryTime());
            tempObj.setChunkUploadUrl(v);
            list.add(tempObj);
        });

        this.fileChunkInfoGateway.saveAll(list);
        return Response.buildSuccess();
    }
}
