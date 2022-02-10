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
package com.minacloud.upms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.command.DeleteByIdCommand;
import com.minacloud.common.base.query.IdQry;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.upms.api.UserService;
import com.minacloud.upms.command.DisableUserCmdExe;
import com.minacloud.upms.command.EnableUserCmdExe;
import com.minacloud.upms.command.UserCreateCmdExe;
import com.minacloud.upms.command.UserDeleteCmdExe;
import com.minacloud.upms.command.UserFindByIdQryExe;
import com.minacloud.upms.command.UserFindByMobileQryExe;
import com.minacloud.upms.command.UserFindByOpenIdQryExe;
import com.minacloud.upms.command.UserFindByUsernameQryExe;
import com.minacloud.upms.command.UserFindPageQryExe;
import com.minacloud.upms.command.UserUpdateCmdExe;
import com.minacloud.upms.command.UserUpdatePwdCmdExe;
import com.minacloud.upms.dto.clientobject.UsersCO;
import com.minacloud.upms.dto.cmd.UserAddCmd;
import com.minacloud.upms.dto.cmd.UserUpdateCmd;
import com.minacloud.upms.dto.cmd.UserUpdatePwdCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCreateCmdExe userCreateCmdExe;
    private final UserUpdateCmdExe userUpdateCmdExe;
    private final UserUpdatePwdCmdExe userUpdatePwdCmdExe;
    private final UserDeleteCmdExe userDeleteCmdExe;
    private final UserFindByIdQryExe userFindByIdQryExe;
    private final UserFindPageQryExe userFindPageQryExe;
    private final UserFindByUsernameQryExe userFindByUsernameQryExe;
    private final UserFindByMobileQryExe userFindByMobileQryExe;
    private final UserFindByOpenIdQryExe userFindByOpenIdQryExe;
    private final EnableUserCmdExe enableUserCmdExe;
    private final DisableUserCmdExe disableUserCmdExe;

    @Override
    public Response createUser(UserAddCmd cmd) {
        return userCreateCmdExe.execute(cmd);
    }

    @Override
    public Response updateUser(UserUpdateCmd cmd) {
        return userUpdateCmdExe.execute(cmd);
    }

    @Override
    public Response deleteUser(DeleteByIdCommand cmd) {
        return userDeleteCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<UsersCO> findById(IdQry qry) {
        return userFindByIdQryExe.execute(qry);
    }

    @Override
    public PageResponse<UsersCO> findPage() {
        return userFindPageQryExe.execute(null);
    }

    @Override
    public SingleResponse<UsersCO> findByUsername(SingleParamQry qry) {
        return userFindByUsernameQryExe.execute(qry);
    }

    @Override
    public SingleResponse<UsersCO> findByMobile(SingleParamQry qry) {
        return userFindByMobileQryExe.execute(qry);
    }

    @Override
    public SingleResponse<UsersCO> findByOpenId(SingleParamQry qry) {
        return userFindByOpenIdQryExe.execute(qry);
    }

    @Override
    public Response setRoleToUser(Long id, Set<Long> roleIds) {
        return null;
    }

    @Override
    public MultiResponse<UsersCO> findRolesByUserId(IdQry qry) {
        return null;
    }

    @Override
    public Response enableUser(IdQry qry) {
        return enableUserCmdExe.execute(qry);
    }

    @Override
    public Response disableUser(IdQry qry) {
        return disableUserCmdExe.execute(qry);
    }

    @Override
    public Response updatePassword(UserUpdatePwdCmd cmd) {
        return userUpdatePwdCmdExe.execute(cmd);
    }
}
