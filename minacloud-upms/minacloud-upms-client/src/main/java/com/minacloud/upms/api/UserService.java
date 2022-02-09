package com.minacloud.upms.api;

/*-
 * #%L
 * minacloud-upms-client
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
