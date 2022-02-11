package com.minacloud.file.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;

/**
 * 分片结果
 */
@Getter
@Setter
@Accessors(chain = true)
public class FileShardingResult {
    /**
     * uploadId
     */
    private String uploadId;
    /**
     * 过期时间
     */
    private Date expiryTime;
    /**
     * 每个分片对应的上传url
     */
    private Map<Integer, String> uploadUrls;
}
