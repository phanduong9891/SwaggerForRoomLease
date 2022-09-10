package com.axonactive.roomLeaseManagement.api;


import com.axonactive.roomLeaseManagement.entity.*;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.MonthlyPaymentRequest;
import com.axonactive.roomLeaseManagement.request.PaidMonthlyPaymentRequest;
import com.axonactive.roomLeaseManagement.service.ContractDealService;
import com.axonactive.roomLeaseManagement.service.Impl.ContractDealServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.MonthlyPaymentServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.MonthlyServiceUsingServiceImpl;
import com.axonactive.roomLeaseManagement.service.MonthlyServiceUsingService;
import com.axonactive.roomLeaseManagement.service.dto.BusinessReportDto;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyPaymentDto;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyServiceUsingDto;
import com.axonactive.roomLeaseManagement.service.mapper.MonthlyPaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(MonthlyPaymentResource.PATH)
public class MonthlyPaymentResource {
    public static final String PATH = "/api/monthlyPayment";
    @Autowired
    private MonthlyPaymentServiceImpl monthlyPaymentService;


    @GetMapping
    public ResponseEntity<List<MonthlyPaymentDto>> getAll(@RequestParam(name = "date1", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                                          @RequestParam(name = "date2", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2) {
        if (null == date1 && null == date2) {
            List<MonthlyPayment> monthlyPaymentList = monthlyPaymentService.getAll();
            return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPaymentList));
        } else {
            List<MonthlyPayment> monthlyPaymentList = monthlyPaymentService.findByPaidDayBetween(date1,date2);
            return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPaymentList));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<MonthlyPaymentDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyPayment monthlyPayment = monthlyPaymentService.findById(id)
                .orElseThrow(ExceptionList::monthlyPaymentNotFound);
        return ResponseEntity.ok().body(MonthlyPaymentMapper.INSTANCE.toDto(monthlyPayment));
    }

    @GetMapping("/showReport")
    public ResponseEntity<BusinessReportDto> showReport(@RequestParam(name = "date1", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
                                                        @RequestParam(name = "date2", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2) {
        return ResponseEntity.ok(monthlyPaymentService.showReport(date1,date2));
    }

    @GetMapping("/Unpaid")
    public ResponseEntity<List<MonthlyPaymentDto>> getUnpaid() {
        List<MonthlyPayment> monthlyPayment = monthlyPaymentService.findByPaid(false);
        if (monthlyPayment.size() == 0)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPayment));
    }


    @PostMapping
    public ResponseEntity<MonthlyPaymentDto> create(@RequestBody MonthlyPaymentRequest monthlyPaymentRequest) throws ResourceNotFoundException {

        MonthlyPayment createMonthlyPayment = monthlyPaymentService.save(monthlyPaymentService.create(monthlyPaymentRequest));

        return ResponseEntity.created(URI.create((MonthlyPaymentResource.PATH + "/" + createMonthlyPayment.getId()))).body(MonthlyPaymentMapper.INSTANCE.toDto(createMonthlyPayment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyPayment monthlyPayment = monthlyPaymentService.findById(id)
                .orElseThrow(ExceptionList::monthlyPaymentNotFound);
        monthlyPaymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPaymentDto> update(@PathVariable(value = "id") Integer monthlyPaymentId, @RequestBody MonthlyPaymentRequest monthlyPaymentRequest) {

        MonthlyPayment monthlyPaymentUpdate = monthlyPaymentService.save(monthlyPaymentService.edit(monthlyPaymentId,monthlyPaymentRequest));

        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDto(monthlyPaymentUpdate));
    }

    @PutMapping("/paidAMonthlyPayment/{id}")
    public ResponseEntity<MonthlyPaymentDto> paidAMonthlyPayment(@PathVariable(value = "id") Integer id, @RequestBody PaidMonthlyPaymentRequest paidMonthlyPaymentRequest) {
        MonthlyPayment editMonthlyPayment = monthlyPaymentService.findById(id).orElseThrow(ExceptionList::monthlyPaymentNotFound);
        editMonthlyPayment.setPaid(paidMonthlyPaymentRequest.isPaid());
        editMonthlyPayment.setPaymentMethod(paidMonthlyPaymentRequest.getPaymentMethod());
        editMonthlyPayment.setPaidDay(LocalDate.now());
        MonthlyPayment paidMonthlyPayment = monthlyPaymentService.save(editMonthlyPayment);

        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDto(paidMonthlyPayment));
    }
}