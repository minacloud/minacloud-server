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

import com.minacloud.common.result.ResultCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum DefaultResultCodeEnum implements ResultCode {
    /**
     * Success
     */
    SUCCESS(ResultStatusEnum.SUCCESS, "Success"),
    /**
     * Fail
     */
    FAIL(ResultStatusEnum.FAIL, "Fail"),
    /**
     * Parameter Illegal
     */
    PARAM_ILLEGAL(ResultStatusEnum.FAIL, "Parameter Illegal"),
    /**
     * Access Denied
     */
    ACCESS_DENIED(ResultStatusEnum.FAIL, "Access Denied"),
    /**
     * Resource Not Found
     */
    RES_NOT_FOUND(ResultStatusEnum.FAIL, "Resource Not Found"),
    /**
     * Method Not Supported
     */
    METHOD_NOT_SUPPORTED(ResultStatusEnum.FAIL, "Method Not Supported"),
    /**
     * Server Error
     */
    SERVER_ERROR(ResultStatusEnum.FAIL, "Server Error"),
    /**
     * Unknown
     */
    UNKNOWN(ResultStatusEnum.UNKNOWN, "Unknown");
    private final String resultStatus;
    private final String resultMessage;

    DefaultResultCodeEnum(ResultStatusEnum resultStatus, String resultMessage) {
        this.resultStatus = resultStatus.getCode();
        this.resultMessage = resultMessage;
    }

    @Override
    public String getResultCode() {
        return name();
    }

    public static DefaultResultCodeEnum getByResultCode(String resultCode) {
        return Arrays.stream(DefaultResultCodeEnum.values()).filter(v -> Objects.equals(v.getResultCode(), resultCode)).findFirst().orElse(UNKNOWN);
    }
}
