/*
 * minacloud-server-adapter - minacloud-server
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
package com.minacloud.upms.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.minacloud.common.base.command.DeleteByIdCommand;
import com.minacloud.common.base.query.IdQry;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.upms.api.UserService;
import com.minacloud.upms.dto.clientobject.UsersCO;
import com.minacloud.upms.dto.cmd.UserAddCmd;
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
     * @param usersCO
     */
    @PutMapping("/user")
    public Response updateSysUser(@RequestBody UsersCO usersCO) {
        userService.updateUser(UserUpdateCmd.of(usersCO));
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
    public MultiResponse<UsersCO> findRolesByUserId(@PathVariable Long id) {
        return userService.findRolesByUserId(IdQry.of(id));
    }

    /**
     * 用户查询
     *
     * @return
     */
    @ApiOperation(value = "用户查询列表")
    @GetMapping("/users")
    public PageResponse<UsersCO> findUsers() {
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
    public Response resetPassword(@RequestBody UsersCO usersCO) {
        UserUpdatePwdCmd cmd = new UserUpdatePwdCmd();
        cmd.setUserId(usersCO.getId());
        cmd.setOldPassword(usersCO.getOldPassword());
        cmd.setNewPassword(usersCO.getNewPassword());
        cmd.setConfirmPassword(usersCO.getNewPassword());
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
        userService.deleteUser(DeleteByIdCommand.of(id));
        return Response.buildSuccess();
    }
}
