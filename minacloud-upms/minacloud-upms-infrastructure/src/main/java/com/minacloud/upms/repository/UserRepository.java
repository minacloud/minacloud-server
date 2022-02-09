package com.minacloud.upms.repository;

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

import com.minacloud.upms.dataobject.UserDO;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<UserDO, Long> {

    @Modifying
    @Query("update sys_user set enabled=1 where id=:id")
    void enableUser(@Param("id") Long id);

    @Modifying
    @Query("update sys_user set enabled=0 where id=:id")
    void disableUser(@Param("id") Long id);

    @Modifying
    @Query("update sys_user set password=:password where id=:userId")
    void updatePassword(@Param("userId") Long userId, @Param("password") String password);

    UserDO findByPhone(String phone);

    UserDO findByOpenId(String openId);

    UserDO findByUsername(String username);
}
