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
package com.minacloud.common.controller;


import com.minacloud.common.handler.HttpProcessHandler;
import com.minacloud.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class GatewayDispatcherController {
    @Autowired
    private HttpProcessHandler httpProcessHandler;

    @RequestMapping(value = "/api/**/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void receiver(HttpServletRequest request, HttpServletResponse response) {
        try {
            httpProcessHandler.handler(request, response);
        } catch (Exception e) {
            LogUtils.error(log, e);
        }
    }

    @RequestMapping(value = "/api/**/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void doUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
        try {
            httpProcessHandler.handler(request, response);
        } catch (Exception e) {
            LogUtils.error(log, e);
        }
    }

    @RequestMapping("/index.html")
    public void renderIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write("Welcome!!!");
        } catch (IOException e) {
            LogUtils.error(log, e);
        }
    }
}
