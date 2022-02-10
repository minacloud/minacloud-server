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
package com.minacloud.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Reader;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private JsonUtil() {
    }

    private static final Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss");
        GSON = gsonBuilder.create();
    }

    public static String toJsonString(Object object) {
        return GSON.toJson(object);
    }

    public static JsonObject toJsonObject(Object object) {
        JsonElement jsonElement = GSON.toJsonTree(object);
        return jsonElement.getAsJsonObject();
    }

    public static JsonElement toJsonElement(Object object) {
        return GSON.toJsonTree(object);
    }

    public static void addProperty(JsonObject object, String key, Object value) {
        object.add(key, toJsonElement(value));
    }

    public static JsonObject toJsonObject(String key1, Object value1) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        return jsonObject;
    }

    public static JsonObject toJsonObject(String key1, Object value1, String key2, Object value2) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        jsonObject.add(key2, toJsonElement(value2));
        return jsonObject;
    }

    public static JsonObject toJsonObject(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        jsonObject.add(key2, toJsonElement(value2));
        jsonObject.add(key3, toJsonElement(value3));
        return jsonObject;
    }

    public static JsonObject toJsonObject(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4, Object value4) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        jsonObject.add(key2, toJsonElement(value2));
        jsonObject.add(key3, toJsonElement(value3));
        jsonObject.add(key4, toJsonElement(value4));
        return jsonObject;
    }

    public static JsonObject toJsonObject(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4, Object value4, String key5, Object value5) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key1, toJsonElement(value1));
        jsonObject.add(key2, toJsonElement(value2));
        jsonObject.add(key3, toJsonElement(value3));
        jsonObject.add(key4, toJsonElement(value4));
        jsonObject.add(key5, toJsonElement(value5));
        return jsonObject;
    }

    public static JsonObject toJsonObject(Map<String, Object> objectMap) {
        JsonObject jsonObject = new JsonObject();
        objectMap.forEach((k, v) -> jsonObject.add(k, toJsonElement(v)));
        return jsonObject;
    }

    public static <T> T parseObject(String json, Class<T> clzz) {
        return GSON.fromJson(json, clzz);
    }

    public static <T> T parseObject(Reader json, Class<T> clzz) {
        return GSON.fromJson(json, clzz);
    }

    public static <T> List<T> parseArray(String json, Class<List<T>> clzz) {
        return GSON.fromJson(json, clzz);
    }

    public static <T> List<T> parseArray(Reader json, Class<List<T>> clzz) {
        return GSON.fromJson(json, clzz);
    }
}
