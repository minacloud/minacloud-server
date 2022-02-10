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


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cola.dto.DTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import java.util.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
public abstract class BaseDO<PK> extends DTO implements Persistable<PK> {
    /**
     * 主键 ID
     */
    @Id
    private PK id;
    /**
     * 创建人
     */
    @CreatedBy
    private String createBy;
    /**
     * 更新人
     */
    @LastModifiedBy
    private String updateBy;
    /**
     * 创建时间
     */
    @CreatedDate
    private Date createTime;
    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateTime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return ObjectUtil.isNull(id);
    }
}
