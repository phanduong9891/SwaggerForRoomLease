package com.axonactive.roomLeaseManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUponTerminationRequest {
    private LocalDate dayCreated;
    private double damageFee;
    private double depositRefund;
    private Integer contractId;
}
