package com.axonactive.roomLeaseManagement.service.Impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TenantServiceImplTest {
    @Autowired
    TenantServiceImpl tenantService;

    @Test
    void tenantMonthRent() {
        assertEquals(0,tenantService.tenantMonthRent());
    }


}