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
package com.minacloud.common.tracer;


import com.minacloud.common.utils.MDCUtil;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.TextMap;

public class TracerUtil {
    private static TracerSpi tracerSpi;

    public static Span getSpan() {
        return tracerSpi.getCurrentSpan();
    }

    public static Span createSpan(String moduleType) {
        return tracerSpi.createSpan(moduleType);
    }

    public static TextMap getSpanMap() {
        return tracerSpi.getSpanMap();
    }

    public static String getTracerId() {
        return tracerSpi.getTracerId();
    }

    public static Tracer getTracer() {
        return tracerSpi.getTracer();
    }

    public static Tracer createTracer(String type) {
        return tracerSpi.createTracer(type);
    }


    public static void putSpan(Span span) {
        tracerSpi.putSpan(span);
    }

    public static void logTraceId(Span span) {
        MDCUtil.logStartedSpan(span);
    }

    public static void clearTraceId() {
        MDCUtil.logStoppedSpan();
    }

    public static void setTracerSpi(TracerSpi tracerSpi) {
        TracerUtil.tracerSpi = tracerSpi;
    }

    public static TracerSpi getTracerSpi() {
        return tracerSpi;
    }
}


