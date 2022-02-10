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


import com.minacloud.common.exception.MinaCloudParamIllegalException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public final class ValidatorUtils {
    private ValidatorUtils() {
    }

    static Validator validator;

    public static Validator getValidator() {
        if (validator == null) {
            synchronized (ValidatorUtils.class) {
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                return validator = factory.getValidator();
            }
        }
        return validator;
    }

    public static <T> T tryValidate(T bean, Class... group) {
        Set<ConstraintViolation<T>> violations = getValidator().validate(bean, group);
        if (!violations.isEmpty()) {
            throw new MinaCloudParamIllegalException(violations.iterator().next().getMessage(), violations);
        }

        return bean;
    }
}
