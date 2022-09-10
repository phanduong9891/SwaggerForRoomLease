package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.service.dto.ContractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "tenant.firstName", target = "firstName")
    @Mapping(source = "tenant.lastName", target = "lastName")
    @Mapping(source = "tenant.phoneNumber", target = "phoneNumber")
    @Mapping(source = "tenant.idCardNumber", target = "idCardNumber")
    @Mapping(source = "tenant.relatives.firstName", target = "relativeFirstName")
    @Mapping(source = "tenant.relatives.relationship", target = "relativeRelationship")
    @Mapping(source = "tenant.relatives.phoneNumber", target = "relativePhoneNumber")
    ContractDto toDto(Contract contract);
    List<ContractDto> toDtos(List<Contract> contractList);

}
