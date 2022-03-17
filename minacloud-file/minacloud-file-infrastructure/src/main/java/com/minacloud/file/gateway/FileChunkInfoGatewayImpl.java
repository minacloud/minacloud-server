/*
 * minacloud-file-infrastructure - minacloud
 * Copyright © 2021 minacloud (lslvxy@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
