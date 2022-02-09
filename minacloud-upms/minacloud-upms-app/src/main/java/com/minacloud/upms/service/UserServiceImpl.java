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

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.query.IdQry;
import com.minacloud.upms.api.UserService;
import com.minacloud.upms.command.UserCreateCmdExe;
import com.minacloud.upms.command.UserFindByIdQryExe;
import com.minacloud.upms.dto.clientobject.UserCO;
import com.minacloud.upms.dto.cmd.UserAddCmd;
import com.minacloud.upms.dto.cmd.UserDeleteCmd;
import com.minacloud.upms.dto.cmd.UserUpdateCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCreateCmdExe userCreateCmdExe;
    private final UserFindByIdQryExe userFindByIdQryExe;

    @Override
    public Response createUser(UserAddCmd cmd) {
        return userCreateCmdExe.execute(cmd);
    }

    @Override
    public Response updateUser(UserUpdateCmd cmd) {
        return null;
    }

    @Override
    public Response deleteUser(UserDeleteCmd cmd) {
        return null;
    }

    @Override
    public SingleResponse<UserCO> findById(IdQry qry) {
        return userFindByIdQryExe.execute(qry);
    }

    @Override
    public PageResponse<UserCO> findPage(IdQry qry) {
        return null;
    }
}
