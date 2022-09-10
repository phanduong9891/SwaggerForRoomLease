package com.axonactive.roomLeaseManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {
    private LocalDate dateSigned;
    private LocalDate dateExpiry;
    private double contractValue;
    private Integer tenantId;
}
