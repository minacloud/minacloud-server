package com.minacloud.file.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.file.api.FileService;
import com.minacloud.file.dto.cmd.UploadFileCmd;
import com.minacloud.file.helper.MinioHelper;
import lombok.RequiredArgsConstructor;
import org.simpleframework.xml.core.Validate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FilesController {
    private final FileService fileService;
    private final MinioHelper minioHelper;

    @PostMapping("/files/upload")
    public Response uploadFiles(@RequestBody @Validate UploadFileCmd cmd) {
        return fileService.getUploadId(cmd);
    }

    @PostMapping("/files/merge")
    public Response merge(String fileName, String uploadId) {
        minioHelper.mergeMultiPartUpload(fileName, uploadId);
        return SingleResponse.of(uploadId);
    }


}
