package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.Owner;
import com.axonactive.roomLeaseManagement.service.dto.OwnerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    OwnerDto toDto(Owner owner);
    List<OwnerDto> toDtos(List<Owner> ownerList);
}
