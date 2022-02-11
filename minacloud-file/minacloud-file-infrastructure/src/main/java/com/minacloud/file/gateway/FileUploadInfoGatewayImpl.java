package com.minacloud.file.gateway;

import com.minacloud.file.convertor.FileUploadInfoDOConvertor;
import com.minacloud.file.dataobject.FileUploadInfoDO;
import com.minacloud.file.domain.FileUploadInfo;
import com.minacloud.file.repository.FileUploadInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileUploadInfoGatewayImpl implements FileUploadInfoGateway {
    private final FileUploadInfoRepository fileUploadInfoRepository;
    private final FileUploadInfoDOConvertor fileUploadInfoDOConvertor;

    @Override
    public void create(FileUploadInfo entity) {
        fileUploadInfoRepository.save(fileUploadInfoDOConvertor.toSource(entity));
    }

    @Override
    public void update(FileUploadInfo entity) {
        fileUploadInfoRepository.save(fileUploadInfoDOConvertor.toSource(entity));
    }

    @Override
    public FileUploadInfo findById(Long aLong) {
        Optional<FileUploadInfoDO> uploadInfoDO = fileUploadInfoRepository.findById(aLong);
        return uploadInfoDO.map(fileUploadInfoDOConvertor::toTarget).orElse(null);
    }

    @Override
    public void removeById(Long aLong) {
        fileUploadInfoRepository.deleteById(aLong);
    }

    @Override
    public void remove(FileUploadInfo entity) {
        fileUploadInfoRepository.delete(fileUploadInfoDOConvertor.toSource(entity));

    }

    @Override
    public void saveAll(List<FileUploadInfo> list) {
        fileUploadInfoRepository.saveAll(fileUploadInfoDOConvertor.toSource(list));
    }

    @Override
    public FileUploadInfo findByFileMd5(String fileMd5) {
        FileUploadInfoDO infoDO = fileUploadInfoRepository.findByFileMd5(fileMd5);
        return fileUploadInfoDOConvertor.toTarget(infoDO);
    }
}
