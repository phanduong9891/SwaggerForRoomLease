package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.entity.ContractDeal;
import com.axonactive.roomLeaseManagement.entity.ContractType;
import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.ContractDealRepository;
import com.axonactive.roomLeaseManagement.request.ContractDealRequest;
import com.axonactive.roomLeaseManagement.service.ContractDealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ContractDealServiceImpl implements ContractDealService {
    @Autowired
    private ContractDealRepository contractDealRepository;
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private ContractServiceImpl contractService;

    @Override
    public List<ContractDeal> getAll() {
        return contractDealRepository.findAll();
    }

    @Override
    public ContractDeal save(ContractDeal contractDeal) {
        return contractDealRepository.save(contractDeal);
    }

    public ContractDeal create(ContractDealRequest contractDealRequest) {
        if (!roomService.findById(contractDealRequest.getRoomId()).isPresent()) {
            throw ExceptionList.roomNotFound();
        }
        if (!contractService.findById(contractDealRequest.getContractId()).isPresent()) {
            throw ExceptionList.contractNotFound();
        }
        ContractDeal createContractDeal = new ContractDeal();

        createContractDeal.setDeposit(contractDealRequest.getDeposit());
        createContractDeal.setRent(contractDealRequest.getRent());
        createContractDeal.setWaterPrice(contractDealRequest.getWaterPrice());
        createContractDeal.setElectricityPrice(contractDealRequest.getElectricityPrice());
        createContractDeal.setMaximumGuest(contractDealRequest.getMaximumGuest());
        createContractDeal.setType(contractDealRequest.getType());
        createContractDeal.setRoom(roomService.findById(contractDealRequest.getRoomId()).get());
        createContractDeal.setContract(contractService.findById(contractDealRequest.getContractId()).get());

        return createContractDeal;

    }

    @Override
    public ContractDeal edit(Integer contractDealId, ContractDealRequest contractDealRequest) {
        if(!contractDealRepository.findById(contractDealId).isPresent()){
            log.info("Cant find contractDeal by id {} ", contractDealId);
            throw ExceptionList.contractDealNotFound();}
        if (!roomService.findById(contractDealRequest.getRoomId()).isPresent()) {
            log.info("Cant find room of the contractDeal by id {} ", contractDealRequest.getRoomId());
            throw ExceptionList.roomNotFound();
        }
        if (!contractService.findById(contractDealRequest.getContractId()).isPresent()) {
            throw ExceptionList.contractNotFound();
        }
        ContractDeal editContractDeal = contractDealRepository.findById(contractDealId).get();
        editContractDeal.setDeposit(contractDealRequest.getDeposit());
        editContractDeal.setRent(contractDealRequest.getRent());
        editContractDeal.setElectricityPrice(contractDealRequest.getElectricityPrice());
        editContractDeal.setWaterPrice(contractDealRequest.getWaterPrice());
        editContractDeal.setRoom(roomService.findById(contractDealRequest.getRoomId()).get());
        editContractDeal.setContract(contractService.findById(contractDealRequest.getContractId()).get());

        return editContractDeal;
    }



    @Override
    public Optional<ContractDeal> findById(Integer id) {
        return contractDealRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        contractDealRepository.deleteById(id);
    }

    @Override
    public Optional<ContractDeal> findByContractTenantPhoneNumber(String phoneNumber) {
        return contractDealRepository.findByContractTenantPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<ContractDeal> findByContractId(Integer id) {
        return contractDealRepository.findByContractId(id);}

}
