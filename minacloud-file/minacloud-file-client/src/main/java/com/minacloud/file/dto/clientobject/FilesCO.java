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

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FilesCO extends BaseCO<Long> {
    private Long userId;
    private Long driveId;
    private Long fileId;
    private Long parentFileId;
    private String name;
    private String fileExtension;
    private Integer status;
    private String category;
    private String contentHash;
    private String contentHashName;
    private String contentType;
    private String downloadUrl;
    private Long size;
    private String thumbnail;
    private String type;
    private String url;
    private String labels;
    private String path;
    private Long deep;
    private Long special;
    private Long inPublic;
    private Long share;
    private Long favorite;
    private String object;
}
