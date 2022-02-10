/*
 * minacloud-server-infrastructure - minacloud-server
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
package com.minacloud.upms.dataobject;


import com.minacloud.common.base.BaseDO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("minacloud_download")
public class DownloadDO extends BaseDO<Long> {


    private Long userId;
    private Long driveId;
    private Integer status;
    private Long type;
    private String src;
    private Long totalSize;
    private Long downloadedSize;
    private Long gid;
    private Long speed;
    private String parent;
    private String attrs;
    private String error;
    private Long parentFileId;
    private String dst;
    private Long zcstatus;
    private Double zcpersent;


}
