/*
 * minacloud-file-client - minacloud
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
package com.minacloud.file.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.file.dto.clientobject.FileUploadInfoCO;
import com.minacloud.file.dto.cmd.SaveFileUploadInfoCmd;

/**
 * @author lanweihong
 * @date 2022/1/4 22:17
 */
public interface FileUploadInfoService {

    /**
     * 保存
     *
     * @param param 参数对象
     * @return
     */
    Response createFileUploadInfo(SaveFileUploadInfoCmd param);

    /**
     * 修改文件状态
     *
     * @param param 参数对象
     * @return
     */
    Response updateFileStatusByFileMd5(SaveFileUploadInfoCmd param);

    /**
     * 根据文件 md5 查询
     *
     * @param fileMd5 文件 md5
     * @return
     */
    SingleResponse<FileUploadInfoCO> getByFileMd5(SingleParamQry qry);
}
