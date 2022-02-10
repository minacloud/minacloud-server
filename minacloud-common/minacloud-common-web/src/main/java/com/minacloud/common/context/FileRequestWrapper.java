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


import com.minacloud.common.enums.DefaultResultCodeEnum;
import com.minacloud.common.exception.MinaCloudBusinessException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileRequestWrapper {

    private InputStream inputStream;

    private String name;

    private String originalFilename;

    private String contentType;

    private byte[] bytes = new byte[0];


    public FileRequestWrapper() {
    }

    public FileRequestWrapper(MultipartFile file) {
        try {
            this.inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new MinaCloudBusinessException(DefaultResultCodeEnum.SERVER_ERROR, "InputStream Read Error");
        }
        this.name = file.getName();
        this.originalFilename = file.getOriginalFilename();
        this.contentType = file.getContentType();
    }

    public byte[] getBytes() {
        if (bytes != null) {
            return Arrays.copyOf(bytes, bytes.length);
        }
        if (inputStream != null) {
            try {
                bytes = FileCopyUtils.copyToByteArray(inputStream);
                return Arrays.copyOf(bytes, bytes.length);
            } catch (IOException e) {
                throw new MinaCloudBusinessException(DefaultResultCodeEnum.SERVER_ERROR, "InputStream Read Error");
            }
        }
        return new byte[0];
    }

    public String getName() {
        return name;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        if (inputStream != null) {
            return inputStream;
        }
        if (bytes != null) {
            return new ByteArrayInputStream(bytes);
        }
        return null;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }
}
