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
package com.minacloud.common.utils;

import cn.hutool.core.util.IdUtil;

/**
 * <p>IdUtil class.</p>
 *
 * @author Laysan
 * @version $Id: $Id
 */
public class IdUtils extends IdUtil {
    /**
     * <p>nextSimpleId.</p>
     *
     * @return a {@link Long} object.
     */
    public static Long nextSimpleId() {
        return getSnowflake(1, 1).nextId();
    }

    /**
     * <p>nextSimpleIdStr.</p>
     *
     * @return a {@link String} object.
     */
    public static String nextSimpleIdStr() {
        return getSnowflake(1, 1).nextIdStr();
    }

    /**
     * <p>nextId.</p>
     *
     * @return a {@link Long} object.
     */
    public static Long nextId(long workerId, long datacenterId) {
        return getSnowflake(workerId, datacenterId).nextId();
    }

    /**
     * <p>nextIdStr.</p>
     *
     * @return a {@link String} object.
     */
    public static String nextIdStr(long workerId, long datacenterId) {
        return getSnowflake(workerId, datacenterId).nextIdStr();
    }
}
