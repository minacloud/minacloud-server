package com.minacloud.file.dto.cmd;

import com.alibaba.cola.dto.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MergeMultipartCmd extends Command {
    private String fileName;

    private String uploadId;
    private String md5;
}
