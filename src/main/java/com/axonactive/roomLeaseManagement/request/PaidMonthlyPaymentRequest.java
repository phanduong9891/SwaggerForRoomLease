package com.axonactive.roomLeaseManagement.request;

import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaidMonthlyPaymentRequest {
    private boolean paid;
    private PaymentMethod paymentMethod;

}
