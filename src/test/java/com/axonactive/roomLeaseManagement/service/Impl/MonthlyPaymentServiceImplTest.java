package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import com.axonactive.roomLeaseManagement.service.MonthlyPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonthlyPaymentServiceImplTest {
    @Autowired
    MonthlyPaymentService monthlyPaymentService;

    @Test
    void testNumberOfMonthlyPayment_shouldReturn5_whenInsertJUNEAnd2022() {
        assertEquals(5, monthlyPaymentService.numberOfMonthlyPayment(Month.JUNE,"2022"));
    }

    @Test
    void testTotalElectricityBill_shouldReturn400000_whenInsertMonthOfMay() {
        assertEquals(400000, monthlyPaymentService.totalElectricityBill(LocalDate.of(2022, 5, 1), LocalDate.of(2022, 5, 31)));
    }

    @Test
    void testTotalWaterBill_shouldReturn300000_whenInsertMonthOfMay() {
        String a = "aaaa";
        System.out.println(a.length());

        assertEquals(300000,monthlyPaymentService.totalWaterBill(LocalDate.of(2022,5,1),LocalDate.of(2022,5,31)));
    }

    @Test
    void testTotalRent_shouldReturn2500000_whenInsertMonthOfMay() {
        assertEquals(2500000,monthlyPaymentService.totalRent(LocalDate.of(2022,5,1),LocalDate.of(2022,5,31)));
    }

    @Test
    void testTotalRevenue_shouldReturn3200000_whenInsertMonthOfMay() {
        assertEquals(3200000,monthlyPaymentService.totalRevenue(LocalDate.of(2022,5,1),LocalDate.of(2022,5,31)));
    }

//    @Test
//    void testPaidAmountThroughMethod_shouldReturn22340000_whenInsertMonthOfJuneAndPaymentMethodIsCash() {
//        assertEquals(22340000, monthlyPaymentService.numberOfPayThroughMethod(LocalDate.of(2022,6,1),LocalDate.of(2022,6,30), PaymentMethod.CASH));
//
//    }
}