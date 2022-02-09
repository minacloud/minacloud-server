package com.minacloud.upms.gateway;

/*-
 * #%L
 * minacloud-upms-infrastructure
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

import com.minacloud.upms.convertor.UserDOConvertor;
import com.minacloud.upms.dataobject.UserDO;
import com.minacloud.upms.domain.SysUser;
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
    public void create(SysUser user) {
        UserDO userDO = userDOConvertor.toSource(user);
        userRepository.save(userDO);
    }

    @Override
    public void update(SysUser user) {
        UserDO userDO = userDOConvertor.toSource(user);
        userRepository.save(userDO);
    }

    @Override
    public SysUser findById(Long userId) {
        Optional<UserDO> byId = userRepository.findById(userId);
        return userDOConvertor.toTarget(byId.orElse(null));
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
    public void removeUser(Long id) {

    }

    @Override
    public SysUser findByField(String fieldName, String param) {
        return null;
    }

    @Override
    public Page<SysUser> findPage() {
        PageRequest pageable = PageRequest.of(1, 10);
        Page<UserDO> all = userRepository.findAll(pageable);
        List<SysUser> sysUsers = userDOConvertor.toTarget(all.toList());
        return new PageImpl(sysUsers, pageable, all.getTotalElements());
    }

    @Override
    public void updatePassword(Long userId, String password) {
        userRepository.updatePassword(userId, password);

    }
}
