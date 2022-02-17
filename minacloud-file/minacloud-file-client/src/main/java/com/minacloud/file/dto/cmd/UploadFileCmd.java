package com.minacloud.file.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UploadFileCmd extends Command {

    private String fileName;

    private String fileMd5;

    private Double fileSize;

    private Double chunkSize = (double) (10 * 1024 * 1024);

    private String contentType = "application/octet-stream";
}
