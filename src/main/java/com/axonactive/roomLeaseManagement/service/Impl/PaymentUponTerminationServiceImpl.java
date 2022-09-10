package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.PaymentUponTerminationRepository;
import com.axonactive.roomLeaseManagement.request.PaymentUponTerminationRequest;
import com.axonactive.roomLeaseManagement.service.PaymentUponTerminationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PaymentUponTerminationServiceImpl implements PaymentUponTerminationService {
    @Autowired
    private PaymentUponTerminationRepository paymentUponTerminationRepository;
    @Autowired
    private ContractServiceImpl contractService;

    @Override
    public List<PaymentUponTermination> getAll() {
        return paymentUponTerminationRepository.findAll();
    }

    @Override
    public PaymentUponTermination save(PaymentUponTermination paymentUponTermination) {
        return paymentUponTerminationRepository.save(paymentUponTermination);
    }

    @Override
    public Optional<PaymentUponTermination> findById(Integer id) {
        return paymentUponTerminationRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        paymentUponTerminationRepository.deleteById(id);
    }

    @Override
    public PaymentUponTermination create(PaymentUponTerminationRequest paymentUponTerminationRequest) {
        if (!contractService.findById(paymentUponTerminationRequest.getContractId()).isPresent()) {
            log.info("Cant find contract of this paymentUponTermination by id {} ", paymentUponTerminationRequest.getContractId());
            throw ExceptionList.paymentUponTerminationNotFound();
        }

        PaymentUponTermination paymentUponTermination = new PaymentUponTermination();

        paymentUponTermination.setDepositRefund(paymentUponTerminationRequest.getDepositRefund());
        paymentUponTermination.setDamageFee(paymentUponTerminationRequest.getDamageFee());
        paymentUponTermination.setDayCreated(paymentUponTerminationRequest.getDayCreated());
        paymentUponTermination.setContract(contractService.findById(paymentUponTerminationRequest.getContractId()).get());

        return paymentUponTermination;
    }

    @Override
    public PaymentUponTermination edit(Integer id, PaymentUponTerminationRequest paymentUponTerminationRequest) {
        if(!paymentUponTerminationRepository.findById(id).isPresent()){
            log.info("Cant find paymentUponTermination with this id {} ", id);
            throw ExceptionList.paymentUponTerminationNotFound();
        }
        if(!contractService.findById(paymentUponTerminationRequest.getContractId()).isPresent()){
            log.info("Cant find contract of this paymentUponTermination with this id {} ", id);
            throw ExceptionList.contractNotFound();
        }
        PaymentUponTermination paymentUponTermination = paymentUponTerminationRepository.findById(id).get();

        paymentUponTermination.setDepositRefund(paymentUponTerminationRequest.getDepositRefund());
        paymentUponTermination.setDamageFee(paymentUponTerminationRequest.getDamageFee());
        paymentUponTermination.setDayCreated(paymentUponTerminationRequest.getDayCreated());
        paymentUponTermination.setContract(contractService.findById(paymentUponTerminationRequest.getContractId()).get());

        return paymentUponTermination;
    }
}