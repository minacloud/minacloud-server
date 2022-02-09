package com.minacloud.upms.gateway;

/*-
 * #%L
 * minacloud-upms-domain
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

import com.minacloud.upms.domain.SysUser;
import org.springframework.data.domain.Page;

public interface UserGateway {
    void create(SysUser user);

    void update(SysUser user);

    SysUser findById(Long userId);

    void enableUser(Long id);

    void disableUser(Long id);

    void removeUser(Long id);

    SysUser findByField(String fieldName, String param);

    Page<SysUser> findPage();

    void updatePassword(Long userId, String password);
}
