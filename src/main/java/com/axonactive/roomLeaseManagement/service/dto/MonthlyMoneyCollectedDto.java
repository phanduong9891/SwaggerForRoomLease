package com.axonactive.roomLeaseManagement.service.dto;

import com.axonactive.roomLeaseManagement.entity.Month;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthlyMoneyCollectedDto {

    private int rent;
    private double electricityBill;
    private double waterBill;
    private String time;
    private String tenantFullName;
    private int roomNumber;
}
