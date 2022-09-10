package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.Assets;
import com.axonactive.roomLeaseManagement.service.dto.AssetsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssetsMapper {
    AssetsMapper INSTANCE = Mappers.getMapper(AssetsMapper.class);

    @Mapping(source = "room.roomNumber", target = "roomNumber")
    AssetsDto toDto(Assets assets);

    List<AssetsDto> toDtos(List<Assets> assetsList);
}
