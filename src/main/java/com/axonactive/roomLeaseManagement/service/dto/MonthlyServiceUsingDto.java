package com.axonactive.roomLeaseManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyServiceUsingDto {
    private double electricityUsage;
    private String time; //ki thanh toan': may need to change name and if change, change under mapper as well
    private int roomNumber;
}
