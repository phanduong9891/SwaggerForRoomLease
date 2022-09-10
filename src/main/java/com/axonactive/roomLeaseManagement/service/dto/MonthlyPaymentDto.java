package com.axonactive.roomLeaseManagement.service.dto;

import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyPaymentDto {
    private String paymentPeriod;
    private double electricityBill;
    private double waterBill;
    private double rent;
    private boolean paid;
    private LocalDate paidDay;
    private PaymentMethod paymentMethod;
    private String tenantFirstName;
    private String tenantLastName;

}
