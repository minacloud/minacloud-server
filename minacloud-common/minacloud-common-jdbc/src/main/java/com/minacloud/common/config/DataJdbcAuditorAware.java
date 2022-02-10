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


import cn.dev33.satoken.stp.StpUtil;
import com.minacloud.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@EnableJdbcAuditing
public class DataJdbcAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        long loginId = 1;
        try {
            loginId = StpUtil.getLoginIdAsLong();
        } catch (Exception e) {
            LogUtils.error(log, "获取当前登录用户失败");
        }
        return Optional.of(loginId);
    }
}
