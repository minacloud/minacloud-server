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
package com.minacloud.common.template;

import com.minacloud.common.result.ResultCode;

public interface ServiceCallback<R> {
    /**
     * check the input parameters
     */
    void checkParameter();

    /**
     * performing member processing
     *
     * @return business result
     */
    R process();

    /**
     * build fail result
     *
     * @param resultCode result body
     * @param errorMsg   error msg
     * @return T error result
     */
    R buildFailureResult(ResultCode resultCode, String errorMsg);

    void buildSuccessResult(R response);
}
