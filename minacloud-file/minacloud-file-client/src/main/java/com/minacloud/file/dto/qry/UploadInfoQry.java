package com.minacloud.file.dto.qry;

import com.alibaba.cola.dto.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UploadInfoQry extends Command {

    private String fileName;

    private String fileMd5;

    private Double fileSize;

    private Double chunkSize;

    private String contentType;
}
