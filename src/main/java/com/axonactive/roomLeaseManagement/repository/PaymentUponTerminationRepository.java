package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentUponTerminationRepository extends JpaRepository<PaymentUponTermination,Integer> {
}
