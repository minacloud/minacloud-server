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
