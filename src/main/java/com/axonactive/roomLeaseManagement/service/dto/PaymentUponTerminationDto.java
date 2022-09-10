package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentUponTerminationDto {
    private LocalDate dayCreated;
    private double damageFee;
    private double depositRefund;
    private String tenantFirstName;
    private String tenantPhoneNumber;
    private String tenantIdCardNumber;
}
