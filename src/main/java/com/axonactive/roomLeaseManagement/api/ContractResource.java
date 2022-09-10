package com.axonactive.roomLeaseManagement.api;


import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.ContractRequest;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.TenantServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.ContractDto;

import com.axonactive.roomLeaseManagement.service.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(ContractResource.PATH)
public class ContractResource {
    public static final String PATH = "/api/contract";
    @Autowired
    private ContractServiceImpl contractService;

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAll() {
        List<Contract> contractList = contractService.getAll();
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDtos(contractList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getById(@PathVariable(value = "id") Integer id, @RequestParam(name = "phoneNumber", required = false) String phoneNumber){
        if (null == phoneNumber) {
            return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contractService.findById(id)
                    .orElseThrow(ExceptionList::contractNotFound)));
        }
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contractService.findByTenantPhoneNumber(phoneNumber)
                .orElseThrow(ExceptionList::contractNotFound)));
    }
    @GetMapping("/aboutExpiredContract")
    public ResponseEntity<List<ContractDto>> getAboutExpiredContract(){
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDtos(contractService.getContractFinishedInTwoMonths()));
    }

    @PostMapping
    public ResponseEntity<ContractDto> create(@RequestBody ContractRequest contractRequest){
        Contract createContract = contractService.save(contractService.create(contractRequest));
        return ResponseEntity.created(URI.create((ContractResource.PATH + "/" + createContract.getId()))).body(ContractMapper.INSTANCE.toDto(createContract));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
        Contract contract = contractService.findById(id)
                .orElseThrow(ExceptionList::contractNotFound);
        contractService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDto> update(@PathVariable(value = "id") Integer contractId, @RequestBody ContractRequest contractRequest) {

        Contract contractUpdate = contractService.save(contractService.edit(contractId,contractRequest));

        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contractUpdate));
    }
}

