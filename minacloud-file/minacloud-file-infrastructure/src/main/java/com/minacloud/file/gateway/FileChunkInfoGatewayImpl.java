package com.minacloud.file.gateway;

import com.minacloud.file.convertor.FileChunkInfoDOConvertor;
import com.minacloud.file.dataobject.FileChunkInfoDO;
import com.minacloud.file.domain.FileChunkInfo;
import com.minacloud.file.repository.FileChunkInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileChunkInfoGatewayImpl implements FileChunkInfoGateway {
    private final FileChunkInfoRepository fileChunkInfoRepository;
    private final FileChunkInfoDOConvertor fileChunkInfoDOConvertor;

    @Override
    public void create(FileChunkInfo entity) {
        fileChunkInfoRepository.save(fileChunkInfoDOConvertor.toSource(entity));
    }

    @Override
    public void update(FileChunkInfo entity) {
        fileChunkInfoRepository.save(fileChunkInfoDOConvertor.toSource(entity));
    }

    @Override
    public FileChunkInfo findById(Long aLong) {
        Optional<FileChunkInfoDO> uploadInfoDO = fileChunkInfoRepository.findById(aLong);
        return uploadInfoDO.map(fileChunkInfoDOConvertor::toTarget).orElse(null);

    }

    @Override
    public void removeById(Long aLong) {
        fileChunkInfoRepository.deleteById(aLong);
    }

    @Override
    public void remove(FileChunkInfo entity) {
        fileChunkInfoRepository.delete(fileChunkInfoDOConvertor.toSource(entity));
    }

    @Override
    public void saveAll(List<FileChunkInfo> list) {
        fileChunkInfoRepository.saveAll(fileChunkInfoDOConvertor.toSource(list));
    }

    @Override
    public List<FileChunkInfo> findByFileMd5AndUploadId(String fileMd5, String uploadId) {
        List<FileChunkInfoDO> infoDOList = fileChunkInfoRepository.findByFileMd5AndUploadId(fileMd5, uploadId);
        return fileChunkInfoDOConvertor.toTarget(infoDOList);

    }

    @Override
    public List<FileChunkInfo> findByFileMd5(String fileMd5) {
        List<FileChunkInfoDO> infoDOList = fileChunkInfoRepository.findByFileMd5(fileMd5);
        return fileChunkInfoDOConvertor.toTarget(infoDOList);
    }
}
