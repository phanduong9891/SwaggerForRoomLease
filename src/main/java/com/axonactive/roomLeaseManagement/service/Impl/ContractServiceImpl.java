package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.entity.ContractType;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.ContractRepository;
import com.axonactive.roomLeaseManagement.request.ContractRequest;
import com.axonactive.roomLeaseManagement.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private TenantServiceImpl tenantService;

    @Override
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Optional<Contract> findById(Integer id) {
        return contractRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        contractRepository.deleteById(id);

    }

    @Override
    public Optional<Contract> findByTenantPhoneNumber(String phoneNumber) {
        return contractRepository.findByTenantPhoneNumberLike(phoneNumber);
    }

    @Override
    public List<Contract> getContractFinishedInTwoMonths() {
        List<Contract> contractList = contractRepository.findAll();
        List<Contract> contractList1 = new ArrayList<>();
        for (Contract c : contractList) {
            if (c.getDateExpiry() != null && LocalDate.now().plusMonths(2).isAfter(c.getDateExpiry()))
                contractList1.add(c);
        }
        return contractList1;
    }

    @Override
    public Contract create(ContractRequest contractRequest) {
        if (!tenantService.findById(contractRequest.getTenantId()).isPresent()) {
            log.info("Cant find tenant of contract by id {} ", contractRequest.getTenantId());
            throw ExceptionList.tenantNotFound();
        }
        Contract contract = new Contract();

        contract.setDateSigned(contractRequest.getDateSigned());
        contract.setDateExpiry(contractRequest.getDateExpiry());
        contract.setContractValue(contractRequest.getContractValue());
        contract.setTenant(tenantService.findById(contractRequest.getTenantId()).get());

        return contract;
    }

    @Override
    public Contract edit(Integer contractId, ContractRequest contractRequest) {
        if(!contractRepository.findById(contractId).isPresent()){
            log.info("Cant find contract id {} ", contractId);
            throw ExceptionList.contractNotFound();
        }

        if (!tenantService.findById(contractRequest.getTenantId()).isPresent()) {
            log.info("Cant find tenant of contract by id {} ", contractRequest.getTenantId());
            throw ExceptionList.tenantNotFound();
        }

        Contract contract = contractRepository.findById(contractId).get();

        contract.setDateSigned(contractRequest.getDateSigned());
        contract.setDateExpiry(contractRequest.getDateExpiry());
        contract.setContractValue(contractRequest.getContractValue());
        contract.setTenant(tenantService.findById(contractRequest.getTenantId()).get());

        return contract;
    }

}
