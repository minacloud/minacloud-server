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

/**
 * @author lanweihong
 * @date 2022/1/5 5:02
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FileChunkInfoCO extends BaseCO<Long> {
    private String uploadId;
    private Integer chunkNumber;
    private String chunkUploadUrl;
    private Date expiryTime;
    private String fileName;
    private String fileMd5;
    private Map<Integer, String> uploadUrls;
}
