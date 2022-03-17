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
package com.minacloud.file.dto.clientobject;

import com.minacloud.common.base.BaseCO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;

/**
 * @author lanweihong
 * @date 2022/1/5 5:02
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FileChunkInfoCO extends BaseCO<Long> {
    private String uploadId;
    private Integer chunkNumber;
    private String chunkUploadUrl;
    private Date expiryTime;
    private String fileName;
    private String fileMd5;
    private Map<Integer, String> uploadUrls;
}
