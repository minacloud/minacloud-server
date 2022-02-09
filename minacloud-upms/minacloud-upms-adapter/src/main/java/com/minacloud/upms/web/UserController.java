/*
 * Copyright © 2021 Laysan (lslvxy@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minacloud.upms.web;

/*-
 * #%L
 * minacloud-upms-adapter
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
import com.minacloud.common.base.query.IdQry;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.upms.api.UserService;
import com.minacloud.upms.dto.clientobject.UserCO;
import com.minacloud.upms.dto.cmd.UserAddCmd;
import com.minacloud.upms.dto.cmd.UserDeleteCmd;
import com.minacloud.upms.dto.cmd.UserUpdateCmd;
import com.minacloud.upms.dto.cmd.UserUpdatePwdCmd;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public Response createUser(@RequestBody UserAddCmd cmd) {
        return userService.createUser(cmd);
    }

    @GetMapping("/user/{id}")
    public Response findById(@PathVariable Long id) {
        return userService.findById(IdQry.of(id));
    }

    /**
     * 查询用户实体对象SysUser
     */
    @GetMapping(value = "/users/name/{username}")
    @ApiOperation(value = "根据用户名查询用户实体")
    public Response selectByUsername(@PathVariable String username) {
        return userService.findByUsername(SingleParamQry.of(username));
    }

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/user/mobile", params = "mobile")
    @ApiOperation(value = "根据手机号查询用户")
    public Response findByMobile(String mobile) {
        return userService.findByMobile(SingleParamQry.of(mobile));
    }

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/users-anon/openId", params = "openId")
    @ApiOperation(value = "根据OpenId查询用户")
    public Response findByOpenId(String openId) {
        return userService.findByOpenId(SingleParamQry.of(openId));
    }

    /**
     * 管理后台修改用户
     *
     * @param userCO
     */
    @PutMapping("/user")
    public Response updateSysUser(@RequestBody UserCO userCO) {
        userService.updateUser(UserUpdateCmd.of(userCO));
        return Response.buildSuccess();
    }

    /**
     * 管理后台给用户分配角色
     *
     * @param id
     * @param roleIds
     */
    @PostMapping("/user/{id}/roles")
    public Response setRoleToUser(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        userService.setRoleToUser(id, roleIds);
        return Response.buildSuccess();
    }

    /**
     * 获取用户的角色
     *
     * @param
     * @return
     */
    @GetMapping("/user/{id}/roles")
    public MultiResponse<UserCO> findRolesByUserId(@PathVariable Long id) {
        return userService.findRolesByUserId(IdQry.of(id));
    }

    /**
     * 用户查询
     *
     * @return
     */
    @ApiOperation(value = "用户查询列表")
    @GetMapping("/users")
    public PageResponse<UserCO> findUsers() {
        return userService.findPage();
    }

    /**
     * 修改用户状态
     *
     * @return
     */
    @ApiOperation(value = "修改用户状态")
    @PutMapping("/user/{id}/enable")
    public Response updateEnabled(@PathVariable Long id) {
        return userService.enableUser(IdQry.of(id));
    }

    /**
     * 修改用户状态
     *
     * @return
     */
    @ApiOperation(value = "禁用用户状态")
    @PutMapping("/user/{id}/disable")
    public Response updateDisabled(@PathVariable Long id) {
        return userService.disableUser(IdQry.of(id));
    }

    /**
     * 管理后台，给用户重置密码
     *
     * @param id
     */
    @PutMapping(value = "/user/{id}/password")
    public Response resetPassword(@PathVariable Long id) {
        UserUpdatePwdCmd cmd = new UserUpdatePwdCmd();
        cmd.setUserId(id);
        userService.updatePassword(cmd);
        return Response.buildSuccess();
    }

    /**
     * 用户自己修改密码
     */
    @PutMapping(value = "/user/password")
    public Response resetPassword(@RequestBody UserCO userCO) {
        UserUpdatePwdCmd cmd = new UserUpdatePwdCmd();
        cmd.setUserId(userCO.getId());
        cmd.setOldPassword(userCO.getOldPassword());
        cmd.setNewPassword(userCO.getNewPassword());
        cmd.setConfirmPassword(userCO.getNewPassword());
        userService.updatePassword(cmd);
        return Response.buildSuccess();
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @DeleteMapping(value = "/user/{id}")
    public Response delete(@PathVariable Long id) {
        userService.deleteUser(UserDeleteCmd.of(id));
        return Response.buildSuccess();
    }


}
