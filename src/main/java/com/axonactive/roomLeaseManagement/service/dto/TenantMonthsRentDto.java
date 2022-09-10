package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class TenantMonthsRentDto {
    private String fullName;
    private Long numberOfMonthsStayed;
}
