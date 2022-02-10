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


import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public interface BaseEnum {
    String getCode();

    String getDescription();

    static <T extends BaseEnum> T getByCode(Class<T> clazz, String code) {
        T[] enumConstants = clazz.getEnumConstants();
        if (Objects.isNull(enumConstants)) {
            return null;
        }
        Optional<T> first = Arrays.stream(enumConstants).filter(v -> Objects.equals(v.getCode(), code)).findFirst();
        return first.orElse(null);
    }
}
