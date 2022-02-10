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

import com.minacloud.common.enums.DefaultResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MinaCloudParamIllegalException extends MinaCloudBusinessException {
    private List<Detail> details;

    public MinaCloudParamIllegalException(String message) {
        super(DefaultResultCodeEnum.PARAM_ILLEGAL, message);
    }

    public MinaCloudParamIllegalException(String property, String message) {
        this(message, Collections.singletonList(new Detail(property, message, null)));
    }

    public MinaCloudParamIllegalException(String message, List<Detail> details) {
        super(DefaultResultCodeEnum.PARAM_ILLEGAL, message);
        this.details = details;
    }

    public MinaCloudParamIllegalException(String message, Set<? extends ConstraintViolation<?>> violations) {
        super(DefaultResultCodeEnum.PARAM_ILLEGAL, message);
        if (null != violations && !violations.isEmpty()) {
            details = new ArrayList<>();
            for (ConstraintViolation<?> violation : violations) {
                details.add(new Detail(violation.getPropertyPath().toString(), violation.getMessage(), null));
            }
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Detail {
        String property;
        String message;
        Object detail;
    }
}
