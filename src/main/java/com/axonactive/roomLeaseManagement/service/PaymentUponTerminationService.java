package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.request.PaymentUponTerminationRequest;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface PaymentUponTerminationService {
    List<PaymentUponTermination> getAll();
    PaymentUponTermination save(PaymentUponTermination paymentUponTermination);
    Optional<PaymentUponTermination> findById(Integer id);
    void deleteById(Integer id);
    PaymentUponTermination create(PaymentUponTerminationRequest paymentUponTerminationRequest);
    PaymentUponTermination edit(Integer id, PaymentUponTerminationRequest paymentUponTerminationRequest);
}
