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
