package com.minacloud.upms.command;

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

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.Response;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.upms.dto.cmd.UserUpdatePwdCmd;
import com.minacloud.upms.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdatePwdCmdExe implements BaseCmdExecutor<UserUpdatePwdCmd, Response> {

    private final UserGateway userGateway;


    @Override
    public Response execute(UserUpdatePwdCmd cmd) {
        Long userId = cmd.getUserId();
        if (StrUtil.isAllBlank(cmd.getOldPassword(), cmd.getNewPassword())) {
            userGateway.updatePassword(userId, null);
        } else {
            if (!StrUtil.equals(cmd.getNewPassword(), cmd.getConfirmPassword())) {
                return Response.buildFailure("500", "密码不一致");
            }
            if (StrUtil.equals(cmd.getNewPassword(), cmd.getOldPassword())) {
                return Response.buildFailure("500", "新密码不能与旧密码相同");
            }
            userGateway.updatePassword(userId, cmd.getNewPassword());
        }

        return Response.buildSuccess();
    }


}
