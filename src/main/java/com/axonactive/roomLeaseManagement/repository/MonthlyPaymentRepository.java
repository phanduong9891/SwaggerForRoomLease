package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment, Integer> {
    List<MonthlyPayment> findByPaidDayBetween(LocalDate date1, LocalDate date2);

    //this may need to change to show full detail instead of just number
    @Query("SELECT COUNT(m.id) FROM MonthlyPayment m " +
            "WHERE m.month = ?1 AND m.year = ?2")
    Integer numberOfMonthlyPayment(Month month, String year);

    @Query("SELECT SUM(m.electricityBill) FROM MonthlyPayment m " +
            "WHERE m.paidDay BETWEEN ?1 AND ?2")
    double totalElectricityBill(LocalDate date1, LocalDate date2);

    @Query("SELECT SUM(m.waterBill) FROM MonthlyPayment m " +
            "WHERE m.paidDay BETWEEN ?1 AND ?2")
    double totalWaterBill(LocalDate date1, LocalDate date2);

    @Query("SELECT SUM(m.rent) FROM MonthlyPayment m " +
            "WHERE m.paidDay BETWEEN ?1 AND ?2")
    double totalRent(LocalDate date1, LocalDate date2);

    @Query("SELECT SUM(m.electricityBill + m.waterBill + m.rent) FROM MonthlyPayment m " +
            "WHERE m.paidDay BETWEEN ?1 AND ?2")
    double totalRevenue(LocalDate date1, LocalDate date2);

    @Query("SELECT SUM(m.electricityBill + m.rent + m.waterBill) FROM MonthlyPayment m " +
            "WHERE (m.paidDay BETWEEN ?1 AND ?2) " +
            "AND m.paymentMethod = ?3 AND m.paid = true")
    Integer paidAmountThroughMethod(LocalDate date1, LocalDate date2, PaymentMethod paymentMethod);


    List<MonthlyPayment> findByPaid(Boolean status);


}
