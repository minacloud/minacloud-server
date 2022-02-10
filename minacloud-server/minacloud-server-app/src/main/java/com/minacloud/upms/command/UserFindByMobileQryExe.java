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
package com.minacloud.upms.command;


import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.upms.convertor.UserCOConvertor;
import com.minacloud.upms.domain.Users;
import com.minacloud.upms.dto.clientobject.UserCO;
import com.minacloud.upms.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFindByMobileQryExe implements BaseCmdExecutor<SingleParamQry, SingleResponse<UserCO>> {

    private final UserGateway userGateway;

    private final UserCOConvertor userCOConvertor;

    @Override
    public SingleResponse<UserCO> execute(SingleParamQry qry) {
        Users users = userGateway.findByPhone(qry.getParam());
        return SingleResponse.of(userCOConvertor.toSource(users));
    }


}
