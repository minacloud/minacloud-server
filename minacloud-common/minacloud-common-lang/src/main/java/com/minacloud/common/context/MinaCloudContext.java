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
package com.minacloud.common.context;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MinaCloudContext {
    private static ThreadLocal<MinaCloudContext> threadLocal = new ThreadLocal<>();
    private Map<String, Object> attributes = new ConcurrentHashMap<>();
    private String tracerId = "";

    public static MinaCloudContext get() {
        MinaCloudContext context = threadLocal.get();
        if (Objects.isNull(context)) {
            context = new MinaCloudContext();
            threadLocal.set(context);
        }
        return context;
    }

    public static void set(MinaCloudContext context) {
        threadLocal.set(context);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void remove(String key) {
        MinaCloudContext context = threadLocal.get();
        if (Objects.nonNull(context)) {
            context.getAttributes().remove(key);
        }
    }

    public MinaCloudContext newInstance() {
        MinaCloudContext context = new MinaCloudContext();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            context.getAttributes().put(entry.getKey(), entry.getValue());
        }
        return context;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getTracerId() {
        return tracerId;
    }

    public void setTracerId(String tracerId) {
        this.tracerId = tracerId;
    }
}
