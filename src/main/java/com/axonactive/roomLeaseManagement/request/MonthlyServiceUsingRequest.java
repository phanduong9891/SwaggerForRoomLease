package com.axonactive.roomLeaseManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.axonactive.roomLeaseManagement.entity.Month;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyServiceUsingRequest {
    private double electricityUsage;
    private Month month;
    private String year;
    private Integer contractDealId;
}
