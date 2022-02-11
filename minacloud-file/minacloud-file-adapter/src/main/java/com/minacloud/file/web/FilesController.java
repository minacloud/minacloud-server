package com.minacloud.file.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.minacloud.file.api.FileService;
import com.minacloud.file.dto.qry.UploadInfoQry;
import com.minacloud.file.helper.MinioHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@RestController
@RequiredArgsConstructor
public class FilesController {
    private final FileService fileService;
    private final MinioHelper minioHelper;

    @PostMapping("/files/upload")
    public Response uploadFiles(MultipartRequest request) {
        MultipartFile file = request.getFile("file");
        UploadInfoQry qry = new UploadInfoQry();
        qry.setFileName(file.getOriginalFilename());
        qry.setFileSize(Double.valueOf(file.getSize()));
        qry.setChunkSize(Double.valueOf(10 * 1024 * 1024));
        qry.setContentType(file.getContentType());

        qry.setFileMd5("123");

        return fileService.getUploadId(qry);
    }

    @PostMapping("/files/merge")
    public Response merge(String fileName, String uploadId) {
        minioHelper.mergeMultiPartUpload(fileName, uploadId);
        return SingleResponse.of(uploadId);
    }


}
