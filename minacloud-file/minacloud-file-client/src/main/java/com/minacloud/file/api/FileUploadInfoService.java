package com.minacloud.file.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.common.base.query.SingleParamQry;
import com.minacloud.file.dto.clientobject.FileUploadInfoCO;
import com.minacloud.file.dto.cmd.SaveFileUploadInfoCmd;

/**
 * @author lanweihong
 * @date 2022/1/4 22:17
 */
public interface FileUploadInfoService {

    /**
     * 保存
     *
     * @param param 参数对象
     * @return
     */
    Response createFileUploadInfo(SaveFileUploadInfoCmd param);

    /**
     * 修改文件状态
     *
     * @param param 参数对象
     * @return
     */
    Response updateFileStatusByFileMd5(SaveFileUploadInfoCmd param);

    /**
     * 根据文件 md5 查询
     *
     * @param fileMd5 文件 md5
     * @return
     */
    SingleResponse<FileUploadInfoCO> getByFileMd5(SingleParamQry qry);
}
