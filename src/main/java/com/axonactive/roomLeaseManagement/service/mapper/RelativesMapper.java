package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.Relatives;
import com.axonactive.roomLeaseManagement.service.dto.RelativesDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RelativesMapper {
    RelativesMapper INSTANCE = Mappers.getMapper(RelativesMapper.class);


    RelativesDto toDto(Relatives relatives);
    List<RelativesDto> toDtos(List<Relatives> relativesList);
}
