package com.minacloud.file.dto.qry;

import com.alibaba.cola.dto.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ListUploadPartsQry extends Command {

    private String objectName;

    private String uploadId;
}
