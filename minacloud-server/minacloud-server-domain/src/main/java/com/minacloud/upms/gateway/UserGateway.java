/*
 * minacloud-server-domain - minacloud-server
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
package com.minacloud.upms.gateway;

import com.minacloud.common.base.BaseGateway;
import com.minacloud.upms.domain.Users;
import org.springframework.data.domain.Page;

public interface UserGateway extends BaseGateway<Users, Long> {
    void enableUser(Long id);

    void disableUser(Long id);

    Users findByOpenId(String param);

    Page<Users> findPage();

    void updatePassword(Long userId, String password);

    Users findByUsername(String param);

    Users findByPhone(String param);
}
