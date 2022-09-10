package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessReportDto {

    private double totalElectricityBill;
    private double totalWaterBill;
    private double totalRent;
    private double totalRevenue;
    private int numberOfPayThroughCash;
    private int numberOfPayThroughCard;

}
