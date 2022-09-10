package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.ContractType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContractDealServiceImplTest {
@Autowired
ContractDealServiceImpl contractDealService;

    @Test
    void findByContractTenantPhoneNumber() {
        assertEquals(0,contractDealService.findByContractTenantPhoneNumber("0913245671"));
    }
}