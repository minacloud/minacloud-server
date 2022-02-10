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


import java.util.Collection;
import java.util.List;

/**
 * <p>BaseConvertor interface.</p>
 *
 * @author Laysan
 * @version $Id: $Id
 * @date 2018-11-23
 */
public interface BaseConvertor<S, T> {

    /**
     * DTO转Entity
     *
     * @param s /
     * @return /
     */
    T toTarget(S s);

    /**
     * Entity转DTO
     *
     * @param t /
     * @return /
     */
    S toSource(T t);

    /**
     * DTO集合转Entity集合
     *
     * @param sourceList /
     * @return /
     */
    List<T> toTarget(Collection<S> sourceList);

    /**
     * Entity集合转DTO集合
     *
     * @param targetList /
     * @return /
     */
    List<S> toSource(Collection<T> targetList);


    /**
     * DTO集合转Entity集合
     *
     * @param sourceList /
     * @return /
     */
    List<T> toTarget(Iterable<S> sourceList);

    /**
     * Entity集合转DTO集合
     *
     * @param targetList /
     * @return /
     */
    List<S> toSource(Iterable<T> targetList);
}
