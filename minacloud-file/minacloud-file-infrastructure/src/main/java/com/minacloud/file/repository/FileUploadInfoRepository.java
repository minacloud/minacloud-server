/*
 * minacloud-file-infrastructure - minacloud
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
package com.minacloud.file.repository;

import com.minacloud.file.dataobject.FileUploadInfoDO;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FileUploadInfoRepository extends PagingAndSortingRepository<FileUploadInfoDO, Long> {
    FileUploadInfoDO findByFileMd5(String fileMd5);
}
