package com.minacloud.file.dataobject;

import com.minacloud.common.base.BaseDO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("minacloud_file_upload_info")
public class FileUploadInfoDO extends BaseDO<Long> {

    private String fileName;

    private String fileMd5;

    private String fileStatus;

    private String uploadId;

    private String fileUrl;

    private Integer totalChunk;
}
