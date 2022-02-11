/*
 * minacloud-server-app - minacloud-server
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
package com.minacloud.server.command;

import com.alibaba.cola.dto.PageResponse;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.common.base.query.IdQry;
import com.minacloud.server.convertor.UserCOConvertor;
import com.minacloud.server.domain.Users;
import com.minacloud.server.dto.clientobject.UsersCO;
import com.minacloud.server.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFindPageQryExe implements BaseCmdExecutor<IdQry, PageResponse<UsersCO>> {
    private final UserGateway userGateway;
    private final UserCOConvertor userCOConvertor;

    @Override
    public PageResponse<UsersCO> execute(IdQry qry) {
        Page<Users> page = userGateway.findPage();
        return PageResponse.of(userCOConvertor.toSource(page.getContent()), (int) page.getTotalElements(), page.getSize(), page.getNumber());
    }
}
