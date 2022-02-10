/*
 * minacloud-common-web - minacloud-common
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
package com.minacloud.common.context;


import com.minacloud.common.exception.MinaCloudBusinessException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class WebRuntimeContext {
    private String traceId;
    private String requestUri;
    private String requestUrl;
    private String method;
    private String requesterClientId;
    private String integratorClientId;
    private String contextPath;
    private String servletPath;
    private int serverPort;
    private String scheme;
    private String serverName;
    private Map<String, String> requestHeaderMap = new HashMap<>();
    private Map<String, String> responseHeaderMap = new HashMap<>();
    private String responseBody;
    private String requestBody;
    private String version;
    private String appId;
    private MinaCloudBusinessException businessException;

    private List<FileRequestWrapper> files;

}
