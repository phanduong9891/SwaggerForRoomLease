package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyMoneyCollectedDto;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyServiceUsingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MonthlyServiceUsingMapper {
    MonthlyServiceUsingMapper INSTANCE = Mappers.getMapper(MonthlyServiceUsingMapper.class);

    @Mapping(target = "time", expression = "java(monthlyServiceUsing.getMonth() + \" \" + monthlyServiceUsing.getYear())")
    @Mapping(source = "contractDeal.room.roomNumber", target = "roomNumber")
    MonthlyServiceUsingDto toServiceUsingDto(MonthlyServiceUsing monthlyServiceUsing);
    List<MonthlyServiceUsingDto> toServiceUsingDtos(List<MonthlyServiceUsing> monthlyServiceUsingList);

    @Mapping(target = "time", expression = "java(monthlyServiceUsing.getMonth() + \" \" + monthlyServiceUsing.getYear())")
    @Mapping(source = "contractDeal.rent", target = "rent")
    @Mapping(source = "contractDeal.waterPrice", target = "waterBill")
    @Mapping(target = "electricityBill", expression = "java(monthlyServiceUsing.getElectricityUsage() * monthlyServiceUsing.getContractDeal().getElectricityPrice())")
    @Mapping(target = "tenantFullName", expression = "java(monthlyServiceUsing.getContractDeal().getContract().getTenant().getLastName() + \" \"" +
            "+ monthlyServiceUsing.getContractDeal().getContract().getTenant().getFirstName())" )
    @Mapping(source = "contractDeal.room.roomNumber", target = "roomNumber")

    MonthlyMoneyCollectedDto toMoneyCollectDto(MonthlyServiceUsing monthlyServiceUsing);
    List<MonthlyMoneyCollectedDto> toMoneyCollectedDtos(List<MonthlyServiceUsing> monthlyServiceUsingList);

}
