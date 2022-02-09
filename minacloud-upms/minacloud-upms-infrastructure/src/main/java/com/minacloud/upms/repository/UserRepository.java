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

public interface UserRepository extends PagingAndSortingRepository<UserDO, Long> {

    @Modifying
    @Query("update UserDO set enabled=true where id=:id")
    void enableUser(Long id);

    @Modifying
    @Query("update UserDO set enabled=false where id=:id")
    void disableUser(Long id);

    @Modifying
    @Query("update UserDO set password=:password where id=:userId")
    void updatePassword(Long userId, String password);
}
