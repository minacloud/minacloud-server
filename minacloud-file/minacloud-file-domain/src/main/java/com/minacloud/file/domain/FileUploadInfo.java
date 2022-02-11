package com.minacloud.file.domain;

import com.alibaba.cola.domain.Entity;
import com.minacloud.common.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
public class FileUploadInfo extends BaseEntity<Long> {

    private String fileName;

    private String fileMd5;

    private String fileStatus;

    private String uploadId;

    private String fileUrl;

    private Integer totalChunk;

}
