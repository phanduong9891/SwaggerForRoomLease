package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.*;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.MonthlyPaymentRepository;
import com.axonactive.roomLeaseManagement.request.MonthlyPaymentRequest;
import com.axonactive.roomLeaseManagement.service.MonthlyPaymentService;
import com.axonactive.roomLeaseManagement.service.dto.BusinessReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MonthlyPaymentServiceImpl implements MonthlyPaymentService {
    @Autowired
    private MonthlyPaymentRepository monthlyPaymentRepository;
    @Autowired
    private ContractServiceImpl contractService;
    @Autowired
    private ContractDealServiceImpl contractDealService;
    @Autowired
    private MonthlyServiceUsingServiceImpl monthlyServiceUsingService;

    @Override
    public List<MonthlyPayment> getAll() {
        return monthlyPaymentRepository.findAll();
    }

    @Override
    public MonthlyPayment save(MonthlyPayment monthlyPayment) {
        return monthlyPaymentRepository.save(monthlyPayment);
    }

    @Override
    public Optional<MonthlyPayment> findById(Integer id) {
        return monthlyPaymentRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        monthlyPaymentRepository.deleteById(id);
    }

    @Override
    public List<MonthlyPayment> findByPaidDayBetween(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.findByPaidDayBetween(date1, date2);
    }

    @Override
    public Integer numberOfMonthlyPayment(Month month, String year) {
        return monthlyPaymentRepository.numberOfMonthlyPayment(month, year);
    }

    @Override
    public double totalElectricityBill(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.totalElectricityBill(date1, date2);
    }

    @Override
    public double totalWaterBill(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.totalWaterBill(date1, date2);
    }

    @Override
    public double totalRent(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.totalRent(date1, date2);
    }

    @Override
    public double totalRevenue(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.totalRevenue(date1, date2);
    }

    @Override
    public BusinessReportDto showReport(LocalDate date1, LocalDate date2) {
        return new BusinessReportDto(totalElectricityBill(date1,date2),
                totalWaterBill(date1,date2),
                totalRent(date1,date2),
                totalRevenue(date1,date2),
                paidAmountThroughMethod(date1,date2,PaymentMethod.CASH),
                paidAmountThroughMethod(date1,date2,PaymentMethod.CARD));
    }


    @Override
    public List<MonthlyPayment> findByPaid(Boolean status) {
        return monthlyPaymentRepository.findByPaid(status);
    }

    @Override
    public Integer paidAmountThroughMethod(LocalDate date1, LocalDate date2, PaymentMethod paymentMethod) {
        return monthlyPaymentRepository.paidAmountThroughMethod(date1, date2, paymentMethod);
    }

    @Override
    public MonthlyPayment create(MonthlyPaymentRequest monthlyPaymentRequest) {
        if (contractService.findById(monthlyPaymentRequest.getContractId()).isPresent()) {
            log.info("Contract of monthlyPayment not found by id {} ", monthlyPaymentRequest.getContractId());
        }

        MonthlyPayment monthlyPayment = new MonthlyPayment();

        monthlyPayment.setPaymentMethod(monthlyPaymentRequest.getPaymentMethod());
        monthlyPayment.setPaidDay(monthlyPaymentRequest.getPaidDay());
        monthlyPayment.setYear(monthlyPaymentRequest.getYear());
        monthlyPayment.setMonth(monthlyPaymentRequest.getMonth());
        monthlyPayment.setPaid(monthlyPaymentRequest.isPaid());
        monthlyPayment.setPaidDay(LocalDate.now());
        monthlyPayment.setRent(monthlyPaymentRequest.getRent());
        monthlyPayment.setWaterBill(monthlyPaymentRequest.getWaterBill());
        monthlyPayment.setElectricityBill(monthlyPaymentRequest.getElectricityBill());
        monthlyPayment.setContract(contractService.findById(monthlyPaymentRequest.getContractId()).get());

        return monthlyPayment;
    }

    @Override
    public MonthlyPayment edit(Integer monthlyPaymentId, MonthlyPaymentRequest monthlyPaymentRequest) {
        if(!monthlyPaymentRepository.findById(monthlyPaymentId).isPresent()){
            log.info("cant find monthlyPayment by id {} ", monthlyPaymentId);
            throw ExceptionList.monthlyPaymentNotFound();
        }
        if (contractService.findById(monthlyPaymentRequest.getContractId()).isPresent()) {
            log.info("Contract of monthlyPayment not found by id {} ", monthlyPaymentRequest.getContractId());
        }
        MonthlyPayment editMonthlyPayment = monthlyPaymentRepository.findById(monthlyPaymentId).get();
        editMonthlyPayment.setMonth(monthlyPaymentRequest.getMonth());
        editMonthlyPayment.setYear(monthlyPaymentRequest.getYear());
        editMonthlyPayment.setElectricityBill(monthlyPaymentRequest.getElectricityBill());
        editMonthlyPayment.setWaterBill(monthlyPaymentRequest.getWaterBill());
        editMonthlyPayment.setRent(monthlyPaymentRequest.getRent());
        editMonthlyPayment.setPaidDay(monthlyPaymentRequest.getPaidDay());
        editMonthlyPayment.setContract(contractService.findById(monthlyPaymentRequest.getContractId()).orElseThrow(ExceptionList::contractNotFound));

        return editMonthlyPayment;
    }




}
