package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.Tenant;
import com.axonactive.roomLeaseManagement.service.dto.TenantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TenantMapper {
    TenantMapper INSTANCE = Mappers.getMapper(TenantMapper.class);

    @Mapping(target = "fullName", expression = "java(tenant.getLastName() + \" \" + tenant.getFirstName())")
    @Mapping(source = "relatives.firstName",target = "relativesFirstName")
    @Mapping(source = "relatives.relationship",target = "relationship")
    @Mapping(source = "relatives.phoneNumber",target = "relativePhoneNumber")
    TenantDto toDto(Tenant tenant);
    List<TenantDto> toDtos(List<Tenant> tenantList);



}
