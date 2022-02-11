package com.minacloud.file.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.minacloud.file.api.FileChunkInfoService;
import com.minacloud.file.command.FileChunkInfoSearchCmdExe;
import com.minacloud.file.command.SaveFileChunkInfoCmdExe;
import com.minacloud.file.dto.clientobject.FileChunkInfoCO;
import com.minacloud.file.dto.cmd.SaveFileChunkInfoCmd;
import com.minacloud.file.dto.qry.FileChunkInfoQry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lanweihong
 * @date 2022/1/4 22:53
 */
@Service
@RequiredArgsConstructor
public class FileChunkInfoServiceImpl implements FileChunkInfoService {

    private final SaveFileChunkInfoCmdExe saveFileChunkInfoCmdExe;
    private final FileChunkInfoSearchCmdExe fileChunkInfoSearchCmdExe;

    @Override
    public Response saveFileChunkInfo(SaveFileChunkInfoCmd cmd) {
        return saveFileChunkInfoCmdExe.execute(cmd);
    }


    @Override
    public MultiResponse<FileChunkInfoCO> findByFileMd5(FileChunkInfoQry qry) {
        return fileChunkInfoSearchCmdExe.execute(qry);
    }

    @Override
    public MultiResponse<FileChunkInfoCO> findByFileMd5AndUploadId(FileChunkInfoQry qry) {
        return fileChunkInfoSearchCmdExe.execute(qry);
    }
}
