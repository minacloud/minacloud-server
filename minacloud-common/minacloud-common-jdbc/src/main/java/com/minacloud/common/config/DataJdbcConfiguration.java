/*
 * minacloud-common-jdbc - minacloud-common
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
package com.minacloud.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.minacloud.common.base.BaseDO;
import com.minacloud.common.utils.IdUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;

@Configuration
@EnableJdbcRepositories("com.minacloud")
public class DataJdbcConfiguration extends AbstractJdbcConfiguration {
    @Bean
    public BeforeSaveCallback<BaseDO> absEntityBeforeSet() {
        return (entity, aggregateChange) -> {
            if (ObjectUtil.isNull(entity.getId())) {
                entity.setId(IdUtils.nextSimpleId());
            }
            if (ObjectUtil.isNull(entity.getDeleted())) {
                entity.setDeleted(Boolean.FALSE);
            }
            return entity;
        };
    }
}
