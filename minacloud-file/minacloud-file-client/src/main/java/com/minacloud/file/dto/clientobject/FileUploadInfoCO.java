package com.minacloud.file.dto.clientobject;

import com.minacloud.common.base.BaseCO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;


@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FileUploadInfoCO extends BaseCO<Long> {
    private String fileName;
    private String fileMd5;
    private String fileStatus;
    private String uploadId;
    private Integer totalChunk;
    private String fileUrl;
    private Date expiryTime;

    /**
     * 每个分片对应的上传url
     */
    private Map<Integer, String> uploadUrls;
}
