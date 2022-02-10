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
package com.minacloud.common.exception;

import com.alibaba.cola.exception.BizException;
import com.minacloud.common.result.ResultCode;

public class MinaCloudBusinessException extends BizException {
    private static final long serialVersionUID = 1L;
    private final ResultCode resultCode;

    public MinaCloudBusinessException(ResultCode resultCode) {
        super(resultCode.getResultCode(), resultCode.getResultMessage());
        this.resultCode = resultCode;
    }

    public MinaCloudBusinessException(ResultCode resultCode, String resultMessage) {
        super(resultCode.getResultCode(), resultMessage);
        this.resultCode = resultCode;
    }

    public ResultCode getErrorCode() {
        return resultCode;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return resultCode.getResultMessage();
        } else {
            return super.getMessage();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MinaCloudBusinessException[");
        if (java.util.Objects.nonNull(resultCode)) {
            stringBuilder.append("resultCode=").append(resultCode.getResultCode()).append(",");
            stringBuilder.append("resultMessage=").append(resultCode.getResultMessage()).append(",");
        }
        stringBuilder.append("extraMessage=").append(getMessage());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
