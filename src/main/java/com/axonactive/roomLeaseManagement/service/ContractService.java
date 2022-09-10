package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.entity.ContractType;
import com.axonactive.roomLeaseManagement.request.ContractRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContractService {
    List<Contract> getAll();
    Contract save(Contract contract);
    Optional<Contract> findById(Integer id);
    void deleteById(Integer id);
    Optional<Contract> findByTenantPhoneNumber(String phoneNumber);
    List<Contract> getContractFinishedInTwoMonths();
    Contract create(ContractRequest contractRequest);
    Contract edit(Integer contractId, ContractRequest contractRequest);

}
