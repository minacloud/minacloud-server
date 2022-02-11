/*
 * minacloud-common-jdbc - minacloud-common
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
package com.minacloud.file.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(MinioPropertiesConfig.class)
public class MinIoClientConfig {
    private MinioPropertiesConfig minioPropertiesConfig;

    @Autowired
    public void setMinioPropertiesConfig(MinioPropertiesConfig minioPropertiesConfig) {
        this.minioPropertiesConfig = minioPropertiesConfig;
    }

    /**
     * 注入minio 客户端
     *
     * @return
     */
    @Bean
    public MinacloudUploadClient minioClient() {
        MinioClient minioClient;
        try {
            minioClient = MinioClient.builder()
                    .endpoint(minioPropertiesConfig.getEndpoint())
                    .credentials(minioPropertiesConfig.getAccessKey(), minioPropertiesConfig.getSecretKey())
                    .build();
        } catch (Exception e) {
            log.error("初始化 Minio 客户端失败：" + e.getMessage());
            throw e;
        }
        return new MinacloudUploadClient(minioClient);
    }
}
