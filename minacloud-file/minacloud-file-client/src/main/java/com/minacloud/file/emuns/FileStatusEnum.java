package com.minacloud.file.emuns;

import com.minacloud.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileStatusEnum implements BaseEnum {
    /**
     * 未上传
     */
    UN_UPLOADED("0", "未上传"),
    /**
     * 已上传
     */
    UPLOADED("1", "已上传"),
    /**
     * 上传中
     */
    UPLOADING("2", "上传中");
    private final String code;
    /**
     * desc
     */
    private final String description;
}
