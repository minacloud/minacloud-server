package com.minacloud.file.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.file.dto.FileOperationResult;
import com.minacloud.file.dto.FileShardingResult;
import com.minacloud.file.dto.cmd.MergeMultipartCmd;
import com.minacloud.file.dto.cmd.UploadFileCmd;
import com.minacloud.file.dto.qry.ListUploadPartsQry;

public interface FileService {
    /**
     * 获取分片上传信息
     *
     * @param param 参数
     * @return
     */
    SingleResponse<FileShardingResult> getUploadId(UploadFileCmd qry);

    /**
     * 检查文件是否存在
     *
     * @param md5 md5
     * @return
     */
    SingleResponse<FileOperationResult> fileExists(SingleParamQry md5);

    /**
     * 查询已上传的分片序号
     *
     * @param 文件名
     * @param uploadId uploadId
     * @return
     */
    MultiResponse<Integer> listUploadParts(ListUploadPartsQry qry);

    /**
     * 分片合并
     *
     * @param param 参数
     * @return
     */
    Response mergeMultipartUpload(MergeMultipartCmd param);
}
