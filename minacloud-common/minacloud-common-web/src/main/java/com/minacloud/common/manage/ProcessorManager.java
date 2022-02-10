/*
 * minacloud-common-web - minacloud-common
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
package com.minacloud.common.manage;

import com.minacloud.common.base.BaseProcessor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class ProcessorManager {
    private static Map<String, BaseProcessor> processorMap = new ConcurrentHashMap<>();

    public static void putAction(String action, BaseProcessor processor) {
        processorMap.put(action, processor);
    }

    public static BaseProcessor getProcessorByAction(String action) {
        return processorMap.getOrDefault(action, null);
    }

    public static boolean hasAction(String action) {
        return processorMap.containsKey(action);
    }

    public static void clear() {
        processorMap.clear();
    }
}
