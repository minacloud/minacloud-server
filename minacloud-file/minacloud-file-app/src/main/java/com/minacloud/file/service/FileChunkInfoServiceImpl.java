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
