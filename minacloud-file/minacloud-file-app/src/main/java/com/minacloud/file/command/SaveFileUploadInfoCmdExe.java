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
