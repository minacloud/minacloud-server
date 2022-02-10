/*
 * minacloud-server-client - minacloud-server
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
package com.minacloud.upms.api;


import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.query.IdQry;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.upms.dto.clientobject.UserCO;
import com.minacloud.upms.dto.cmd.UserAddCmd;
import com.minacloud.upms.dto.cmd.UserDeleteCmd;
import com.minacloud.upms.dto.cmd.UserUpdateCmd;
import com.minacloud.upms.dto.cmd.UserUpdatePwdCmd;

import java.util.Set;

public interface UserService {
    Response createUser(UserAddCmd cmd);

    Response updateUser(UserUpdateCmd cmd);

    Response deleteUser(UserDeleteCmd cmd);

    SingleResponse<UserCO> findById(IdQry qry);

    PageResponse<UserCO> findPage();

    SingleResponse<UserCO> findByUsername(SingleParamQry qry);

    SingleResponse<UserCO> findByMobile(SingleParamQry qry);

    SingleResponse<UserCO> findByOpenId(SingleParamQry openId);

    Response setRoleToUser(Long id, Set<Long> roleIds);

    MultiResponse<UserCO> findRolesByUserId(IdQry qry);

    Response enableUser(IdQry qry);

    Response disableUser(IdQry qry);

    Response updatePassword(UserUpdatePwdCmd cmd);
}
