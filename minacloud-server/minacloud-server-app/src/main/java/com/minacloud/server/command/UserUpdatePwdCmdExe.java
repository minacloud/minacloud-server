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

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.Response;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.server.dto.cmd.UserUpdatePwdCmd;
import com.minacloud.server.gateway.UserGateway;
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
