package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractInfoDto {

    private Integer tenantId;
    private String tenantFirstName;
    private String tenantLastName;
    private LocalDate dateSigned;
    private LocalDate dateExpiry;
    private double totalMoney;
}
