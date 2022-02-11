package com.minacloud.file.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class FileOperationResult {
    private String url;

    /**
     * 状态：0-未上传，1-已上传，2-上传中
     */
    private String status;

    /**
     * 已上传分片列表
     */
    private List<Integer> chunkUploadedList;
}
