package com.minacloud.file.service;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.file.api.FileUploadInfoService;
import com.minacloud.file.command.FileUploadInfoSearchCmdExe;
import com.minacloud.file.command.SaveFileUploadInfoCmdExe;
import com.minacloud.file.command.UpdateStatusByMd5CmdExe;
import com.minacloud.file.dto.clientobject.FileUploadInfoCO;
import com.minacloud.file.dto.cmd.SaveFileUploadInfoCmd;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author lanweihong
 * @date 2022/1/4 22:22
 */
@Service
@AllArgsConstructor
public class FileUploadInfoServiceImpl implements FileUploadInfoService {

    private final SaveFileUploadInfoCmdExe saveFileUploadInfoCmdExe;
    private final FileUploadInfoSearchCmdExe fileUploadInfoSearchCmdExe;
    private final UpdateStatusByMd5CmdExe updateStatusByMd5CmdExe;


    @Override
    public Response createFileUploadInfo(SaveFileUploadInfoCmd cmd) {
        return saveFileUploadInfoCmdExe.execute(cmd);
    }


    @Override
    public Response updateFileStatusByFileMd5(SaveFileUploadInfoCmd cmd) {
        return updateStatusByMd5CmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<FileUploadInfoCO> getByFileMd5(SingleParamQry qry) {
        return fileUploadInfoSearchCmdExe.execute(qry);
    }
}
