package com.axonactive.roomLeaseManagement.service.mapper;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.service.dto.PaymentUponTerminationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentUponTerminationMapper {
    PaymentUponTerminationMapper INSTANCE = Mappers.getMapper(PaymentUponTerminationMapper.class);


    @Mapping(source = "contract.tenant.firstName", target = "tenantFirstName")
    @Mapping(source = "contract.tenant.phoneNumber", target = "tenantPhoneNumber")
    @Mapping(source = "contract.tenant.idCardNumber", target = "tenantIdCardNumber")

    PaymentUponTerminationDto toDto(PaymentUponTermination paymentUponTermination);

    List<PaymentUponTerminationDto> toDtos(List<PaymentUponTermination> paymentUponTerminationList);
}
