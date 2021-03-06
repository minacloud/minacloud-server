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
package com.minacloud.common.enums;

import com.minacloud.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>DeleteStatusEnum class.</p>
 *
 * @author Laysan
 * @version $Id: $Id
 */
@Getter
@AllArgsConstructor
public enum DeleteStatusEnum implements BaseEnum {
    /**
     * 正常
     */
    NORMAL("0", "正常"),
    /**
     * 已删除
     */
    DELETED("1", "已删除");
    /**
     * code
     */
    private String code;
    /**
     * desc
     */
    private String description;
}
