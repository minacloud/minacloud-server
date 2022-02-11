package com.minacloud.file.domain;

import com.alibaba.cola.domain.Entity;
import com.minacloud.common.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
public class FileChunkInfo extends BaseEntity<Long> {

    private String fileMd5;

    private Integer chunkNumber;

    private String chunkUploadUrl;

    private Date expiryTime;

    private String uploadId;
}
