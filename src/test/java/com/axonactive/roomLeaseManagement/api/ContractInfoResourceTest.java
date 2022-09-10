package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.ContractDeal;
import com.axonactive.roomLeaseManagement.service.Impl.ContractDealServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContractDealResourceTest {
@Autowired
    ContractDealServiceImpl contractDealService;
}