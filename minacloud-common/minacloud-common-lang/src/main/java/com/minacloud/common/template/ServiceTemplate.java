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


import com.alibaba.cola.dto.Response;
import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import com.minacloud.common.exception.MinaCloudParamIllegalException;
import com.minacloud.common.result.ResultCode;
import com.minacloud.common.utils.JsonUtil;
import com.minacloud.common.utils.LogUtils;
import com.minacloud.common.utils.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import java.util.Locale;
import java.util.Set;

/**
 * ServiceTemplate
 */
@Slf4j
public class ServiceTemplate {
    /**
     * member processing templates
     *
     * @param request  request parameters
     * @param scenario business scenario event code
     * @param callback member callback template
     */
    public static <P, R> R execute(P request, String scenario, ServiceCallback<R> callback) {

        R response = null;
        try {
            // print invocation request log
            pointInvocationRequestLog(scenario, request);

            // parameter check
            parameterValidator(request, callback);

            // member process
            response = callback.process();

            // fill success response
            callback.buildSuccessResult(response);

        } catch (MinaCloudBusinessException e) {
            LogUtils.warn(log, e, request);
            ResultCode errorCode = e.getErrorCode();
            response = callback.buildFailureResult(errorCode, e.getMessage());
        } catch (Throwable e) {
            LogUtils.error(log, e, request);
            response = callback.buildFailureResult(DefaultResultCodeEnum.UNKNOWN, e.getMessage());

        } finally {
            if (response instanceof Response) {
                // print biz log
                pointInvocationResponseLog(scenario, (Response) response);
            }
        }
        return response;
    }

    /**
     * print invocation request log
     */
    private static void pointInvocationRequestLog(String scenario, Object args) {
        LogUtils.info(log, scenario, " invoke params:", JsonUtil.toJsonString(args));
    }

    /**
     * print invocation response log
     */
    private static <R, T> void pointInvocationResponseLog(String scenario, Response result) {
        LogUtils.info(log, scenario, " invoke result:", JsonUtil.toJsonString(result));

    }

    /**
     * parameter validatorRefundProcessor
     */
    private static <U> void parameterValidator(U request, ServiceCallback callback) {
        // parameter check
        Locale.setDefault(new Locale("en"));

        Set<ConstraintViolation<U>> constraintViolations;
        try {
            constraintViolations = ValidatorUtils.getValidator().validate(request);
        } catch (IllegalArgumentException e) {
            throw new MinaCloudParamIllegalException(e.getMessage());
        }

        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<U> error = constraintViolations.iterator().next();
            throw new MinaCloudParamIllegalException(error.getPropertyPath() + " " + error.getMessage());
        }
        // customize parameter check
        callback.checkParameter();
    }

}

