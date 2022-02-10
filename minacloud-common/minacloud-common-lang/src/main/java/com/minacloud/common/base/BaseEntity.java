/*
 * minacloud-common-lang - minacloud-common
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
package com.minacloud.common.base;


import com.alibaba.cola.dto.DTO;
import com.minacloud.common.utils.ValidatorUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
public abstract class BaseEntity<PK> extends DTO {
    /**
     * 主键 ID
     */
    private PK id;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 删除人
     */
    private String deleteBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除时间
     */
    private Date deletedTime;
    /**
     * 是否删除
     */
    private Boolean deleted;


    public void tryValidate(Class<?>... groups) {
        ValidatorUtils.tryValidate(this, groups);
    }

}
