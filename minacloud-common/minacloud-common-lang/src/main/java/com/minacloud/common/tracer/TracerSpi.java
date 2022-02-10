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


import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.propagation.TextMap;

public interface TracerSpi {
    Span getCurrentSpan();

    TextMap getSpanMap();

    String getTracerId();

    Tracer createTracer(String tracerType);

    Span createSpan(String moduleType);

    Tracer getTracer();

    void clear();

    void putSpan(Span span);

    void setContextCarrier(SpanBuilder spanBuilder, Object contextCarrier);

    Object createContextCarrier();
}
