package com.minacloud.file.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.minacloud.file.dto.clientobject.FileChunkInfoCO;
import com.minacloud.file.dto.cmd.SaveFileChunkInfoCmd;
import com.minacloud.file.dto.qry.FileChunkInfoQry;

public interface FileChunkInfoService {

    /**
     * 保存
     *
     * @param param
     * @return
     */
    Response saveFileChunkInfo(SaveFileChunkInfoCmd cmd);

    MultiResponse<FileChunkInfoCO> findByFileMd5(FileChunkInfoQry qry);

    MultiResponse<FileChunkInfoCO> findByFileMd5AndUploadId(FileChunkInfoQry qry);
}
