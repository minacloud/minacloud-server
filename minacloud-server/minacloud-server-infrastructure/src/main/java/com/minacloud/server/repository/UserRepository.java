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
package com.minacloud.server.repository;

import com.minacloud.server.dataobject.UsersDO;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<UsersDO, Long> {
    @Modifying
    @Query("update minacloud_users set status=1 where id=:id")
    void enableUser(@Param("id") Long id);

    @Modifying
    @Query("update minacloud_users set status=0 where id=:id")
    void disableUser(@Param("id") Long id);

    @Modifying
    @Query("update minacloud_users set password=:password where id=:userId")
    void updatePassword(@Param("userId") Long userId, @Param("password") String password);

    UsersDO findByMobile(String phone);

    UsersDO findByUsername(String username);
}
