package com.axonactive.roomLeaseManagement.request;

import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthlyPaymentRequest {
    private Month month;
    private String year;
    private double electricityBill;
    private double waterBill;
    private double rent;
    private boolean paid;
    private LocalDate paidDay;
    private PaymentMethod paymentMethod;
    private Integer contractId;
}
