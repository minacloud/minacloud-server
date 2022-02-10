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

import com.minacloud.common.tracer.TracerUtil;
import io.opentracing.Span;
import org.slf4j.MDC;

public class MDCUtil {
    public static final String MDC_TRACER_ID = "TracerId";
    public static final String MDC_TENANT_ID = "tenantId";

    public static void logStartedSpan(Span span) {
        if (span != null) {
            MDC.put(MDC_TRACER_ID, TracerUtil.getTracerId());
        }
    }

    public static void logStoppedSpan() {
        MDC.remove(MDC_TRACER_ID);
        Span span = TracerUtil.getSpan();
        if (span != null) {
            MDC.put(MDC_TRACER_ID, TracerUtil.getTracerId());
        }
    }

    public static void logTenantId(String tenantId) {
        MDC.put(MDC_TENANT_ID, tenantId);
    }

    public static void clearTenantId() {
        MDC.remove(MDC_TENANT_ID);
    }
}
