package com.minacloud.upms.service;

/*-
 * #%L
 * minacloud-upms-app
 * %%
 * Copyright (C) 2021 - 2022 minacloud
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
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
import com.minacloud.upms.dto.clientobject.UserCO;
import com.minacloud.upms.dto.cmd.UserAddCmd;
import com.minacloud.upms.dto.cmd.UserDeleteCmd;
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
    public Response deleteUser(UserDeleteCmd cmd) {
        return userDeleteCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<UserCO> findById(IdQry qry) {
        return userFindByIdQryExe.execute(qry);
    }

    @Override
    public PageResponse<UserCO> findPage() {
        return userFindPageQryExe.execute(null);
    }

    @Override
    public SingleResponse<UserCO> findByUsername(SingleParamQry qry) {
        return userFindByUsernameQryExe.execute(qry);
    }

    @Override
    public SingleResponse<UserCO> findByMobile(SingleParamQry qry) {
        return userFindByMobileQryExe.execute(qry);
    }

    @Override
    public SingleResponse<UserCO> findByOpenId(SingleParamQry qry) {
        return userFindByOpenIdQryExe.execute(qry);
    }

    @Override
    public Response setRoleToUser(Long id, Set<Long> roleIds) {
        return null;
    }

    @Override
    public MultiResponse<UserCO> findRolesByUserId(IdQry qry) {
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
