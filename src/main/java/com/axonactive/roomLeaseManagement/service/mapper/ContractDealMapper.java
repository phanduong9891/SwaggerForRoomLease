package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.ContractDeal;
import com.axonactive.roomLeaseManagement.service.dto.ContractDealDto;
import com.axonactive.roomLeaseManagement.service.dto.TenantMonthsRentDto;
import com.axonactive.roomLeaseManagement.service.dto.TenantRoomStayDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContractDealMapper {
    ContractDealMapper INSTANCE = Mappers.getMapper(ContractDealMapper.class);

    @Mapping(source = "room.roomNumber",target = "roomNumber")
    @Mapping(source = "room.roomType",target = "roomType")
    @Mapping(target = "tenantFullName",expression = "java(contractDeal.getContract().getTenant().getLastName() + \" \" + " +
            "contractDeal.getContract().getTenant().getFirstName())")
    @Mapping(source = "contract.tenant.idCardNumber",target = "tenantIdCardNumber")
    ContractDealDto toDto(ContractDeal contractDeal);
    List<ContractDealDto> toDtos(List<ContractDeal> ContractDeal);

    @Mapping(target = "fullName", expression = "java(contractDeal.getContract().getTenant().getLastName() + \" \" + " +
            "contractDeal.getContract().getTenant().getFirstName())")
    @Mapping(source = "room.roomNumber",target = "roomNumber")
    @Mapping(source = "contract.tenant.phoneNumber",target = "phoneNumber")
    TenantRoomStayDto toTenantAndRoomDto(ContractDeal contractDeal);


}
