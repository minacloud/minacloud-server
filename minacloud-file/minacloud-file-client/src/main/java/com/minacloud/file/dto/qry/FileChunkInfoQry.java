package com.minacloud.file.dto.qry;

import com.alibaba.cola.dto.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class FileChunkInfoQry extends Command {

    private Date expiryTime;
    private String fileName;
    private String fileMd5;
    private List<String> uploadUrls;
    private String uploadId;

    public FileChunkInfoQry(String fileMd5, String uploadId) {
        this.setFileMd5(fileMd5);
        this.setUploadId(uploadId);
    }

    public FileChunkInfoQry(String fileMd5) {
        this.setFileMd5(fileMd5);
    }
}
