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
package com.minacloud.common.processor;


import cn.hutool.core.text.CharSequenceUtil;
import com.minacloud.common.base.BaseProcessor;
import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import com.minacloud.common.manage.ProcessorManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Configuration
@Slf4j
public class RequestProcessorListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ProcessorManager.clear();
        Class<? extends Annotation> annotationClass = Processor.class;
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(annotationClass);
        Set<Map.Entry<String, Object>> entitySet = beansWithAnnotation.entrySet();
        for (Map.Entry<String, Object> entry : entitySet) {
            Object value = entry.getValue();
            Class<?> clazz = value.getClass();
            Processor componentDesc = AnnotationUtils.findAnnotation(clazz, Processor.class);
            String action = Objects.requireNonNull(componentDesc).value();
            if (ProcessorManager.hasAction(action)) {
                throw new MinaCloudBusinessException(DefaultResultCodeEnum.SERVER_ERROR, "[" + action + "] Action Config Duplicated");
            }
            log.info("register action ===> {}", action);
            action = CharSequenceUtil.removePrefix(action, "/");
            ProcessorManager.putAction(action, (BaseProcessor) value);
        }
    }
}
