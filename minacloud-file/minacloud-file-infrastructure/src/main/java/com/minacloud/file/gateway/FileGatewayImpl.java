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

import com.minacloud.file.convertor.FilesDOConvertor;
import com.minacloud.file.dataobject.FilesDO;
import com.minacloud.file.domain.Files;
import com.minacloud.file.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileGatewayImpl implements FileGateway {
    private final FilesRepository filesRepository;
    private final FilesDOConvertor filesDOConvertor;

    @Override
    public void create(Files entity) {
        filesRepository.save(filesDOConvertor.toSource(entity));
    }

    @Override
    public void update(Files entity) {
        filesRepository.save(filesDOConvertor.toSource(entity));
    }

    @Override
    public Files findById(Long aLong) {
        Optional<FilesDO> uploadInfoDO = filesRepository.findById(aLong);
        return uploadInfoDO.map(filesDOConvertor::toTarget).orElse(null);
    }

    @Override
    public void removeById(Long aLong) {
        filesRepository.deleteById(aLong);
    }

    @Override
    public void remove(Files entity) {
        filesRepository.delete(filesDOConvertor.toSource(entity));
    }

    @Override
    public void saveAll(List<Files> list) {
        filesRepository.saveAll(filesDOConvertor.toSource(list));
    }
}
