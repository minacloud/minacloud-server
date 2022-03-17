/*
 * minacloud-file-domain - minacloud
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
package com.minacloud.file.gateway;

import com.minacloud.common.base.BaseGateway;
import com.minacloud.file.domain.FileChunkInfo;

import java.util.List;

public interface FileChunkInfoGateway extends BaseGateway<FileChunkInfo, Long> {

    List<FileChunkInfo> findByFileMd5AndUploadId(String fileMd5, String uploadId);

    List<FileChunkInfo> findByFileMd5(String fileMd5);
}
