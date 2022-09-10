package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Contract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ContractServiceImplTest {
    @Autowired
    ContractServiceImpl contractService;


    @Test//may need to ask...results are correct but weird
    void testGetContractFinishedInTwoMonth_shouldReturnAListOfContractFinishedInLessThan2Months_whenRunTheMethod() {
        List<Contract> contractList = new ArrayList<>();
        contractList = contractService.getContractFinishedInTwoMonths();
        for (Contract c : contractList) {
            System.out.println(c.toString());}
    }
}