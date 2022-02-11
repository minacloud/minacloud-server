package com.minacloud.file.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "minacloud.minio")
public class MinioPropertiesConfig {

    /**
     * 服务器 URL
     */
    private String endpoint;

    /**
     * Access key
     */
    private String accessKey;

    /**
     * Secret key
     */
    private String secretKey;

    /**
     * 默认的 bucket 名称
     */
    private String bucketName;

    /**
     * 运行上传的文件类型
     */
    private String allowFileType;

    /**
     * 分片上传有效期（单位：秒）
     */
    private Integer chunkUploadExpirySecond;

}
