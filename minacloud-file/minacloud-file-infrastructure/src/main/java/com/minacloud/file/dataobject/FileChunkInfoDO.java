package com.minacloud.file.dataobject;

import com.minacloud.common.base.BaseDO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("minacloud_file_chunk_info")
public class FileChunkInfoDO extends BaseDO<Long> {

    private String fileMd5;

    private Integer chunkNumber;

    private String chunkUploadUrl;

    private Date expiryTime;

    private String uploadId;
}
