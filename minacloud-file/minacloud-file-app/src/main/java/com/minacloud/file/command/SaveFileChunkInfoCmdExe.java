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
