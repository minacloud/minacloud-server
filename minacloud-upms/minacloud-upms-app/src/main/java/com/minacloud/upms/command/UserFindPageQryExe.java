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

import com.alibaba.cola.dto.PageResponse;
import com.minacloud.common.base.BaseCmdExecutor;
import com.minacloud.common.base.query.IdQry;
import com.minacloud.upms.convertor.UserCOConvertor;
import com.minacloud.upms.domain.SysUser;
import com.minacloud.upms.dto.clientobject.UserCO;
import com.minacloud.upms.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFindPageQryExe implements BaseCmdExecutor<IdQry, PageResponse<UserCO>> {

    private final UserGateway userGateway;

    private final UserCOConvertor userCOConvertor;

    @Override
    public PageResponse<UserCO> execute(IdQry qry) {
        Page<SysUser> page = userGateway.findPage();
        return PageResponse.of(userCOConvertor.toSource(page.getContent()), (int) page.getTotalElements(), page.getSize(), page.getNumber());
    }


}
