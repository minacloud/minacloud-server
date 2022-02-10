/*
 * minacloud-common-lang - minacloud-common
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
package com.minacloud.common.constant;


import java.time.format.DateTimeFormatter;

public interface MinaCloudConstants {
    String RESULT_KEY = "result";

    /**
     * BUFFER_SIZE
     */
    int BUFFER_SIZE = 1024;
    /**
     * CONTENT_TYPE_FORM_URLENCODED
     */
    String CONTENT_TYPE_HEADER = "content-type";
    String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";
    String CONTENT_TYPE_FORM_URLENCODED_UTF8 = "application/x-www-form-urlencoded; charset=utf-8";
    /**
     * CONTENT_TYPE_JSON
     */
    String CONTENT_TYPE_JSON = "application/json";
    String CONTENT_TYPE_JSON_UTF8 = "application/json; charset=utf-8";
    /**
     * Constant <code>DATE_YYYY_MM_DD="yyyy-MM-dd"</code>
     */
    String DEFAULT_DATE = "yyyy-MM-dd";
    /**
     * Constant <code>DEFAULT_DATE_FORMATTER="yyyy-MM-dd"</code>
     */
    DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE);
    /**
     * Constant <code>DEFAULT_DATE_TIME="yyyy-MM-dd HH:mm:ss"</code>
     */
    String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * Constant <code>DEFAULT_DATE_TIME_FORMATTER="yyyy-MM-dd HH:mm:ss"</code>
     */
    DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME);

    /**
     * Constant <code>DEFAULT_PASSWORD="123456"</code>
     */
    String DEFAULT_PASSWORD = "123456";
}
