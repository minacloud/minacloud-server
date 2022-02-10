/*
 * minacloud-server-infrastructure - minacloud-server
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
package com.minacloud.upms.gateway;

import cn.hutool.core.util.StrUtil;
import com.minacloud.common.constant.MinaCloudConstants;
import com.minacloud.common.utils.PasswordUtil;
import com.minacloud.upms.convertor.UserDOConvertor;
import com.minacloud.upms.dataobject.UsersDO;
import com.minacloud.upms.domain.Users;
import com.minacloud.upms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserDOConvertor userDOConvertor;

    @Override
    public void create(Users user) {
        UsersDO usersDO = userDOConvertor.toSource(user);
        userRepository.save(usersDO);
    }

    @Override
    public void update(Users user) {
        UsersDO usersDO = userDOConvertor.toSource(user);
        userRepository.save(usersDO);
    }

    @Override
    public Users findById(Long userId) {
        Optional<UsersDO> byId = userRepository.findById(userId);
        return userDOConvertor.toTarget(byId.orElse(null));
    }

    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void remove(Users user) {
        UsersDO usersDO = userDOConvertor.toSource(user);
        userRepository.delete(usersDO);
    }

    @Override
    public void enableUser(Long id) {
        userRepository.enableUser(id);
    }

    @Override
    public void disableUser(Long id) {
        userRepository.disableUser(id);
    }

    @Override
    public Users findByOpenId(String param) {
        UsersDO usersDO = userRepository.findByUsername(param);
        return userDOConvertor.toTarget(usersDO);
    }

    @Override
    public Page<Users> findPage() {
        PageRequest pageable = PageRequest.of(1, 10);
        Page<UsersDO> all = userRepository.findAll(pageable);
        List<Users> users = userDOConvertor.toTarget(all.toList());
        return new PageImpl(users, pageable, all.getTotalElements());
    }

    @Override
    public void updatePassword(Long userId, String password) {
        if (StrUtil.isBlank(password)) {
            password = MinaCloudConstants.DEFAULT_PASSWORD;
        }
        password = PasswordUtil.encrypt(password);
        userRepository.updatePassword(userId, password);
    }

    @Override
    public Users findByUsername(String param) {
        UsersDO usersDO = userRepository.findByUsername(param);
        return userDOConvertor.toTarget(usersDO);
    }

    @Override
    public Users findByPhone(String param) {
        UsersDO usersDO = userRepository.findByMobile(param);
        return userDOConvertor.toTarget(usersDO);
    }
}
