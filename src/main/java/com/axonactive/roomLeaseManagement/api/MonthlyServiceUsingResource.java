package com.axonactive.roomLeaseManagement.api;


import com.axonactive.roomLeaseManagement.entity.ContractDeal;
import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.MonthlyServiceUsingRequest;
import com.axonactive.roomLeaseManagement.service.ContractDealService;
import com.axonactive.roomLeaseManagement.service.Impl.MonthlyServiceUsingServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyMoneyCollectedDto;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyServiceUsingDto;
import com.axonactive.roomLeaseManagement.service.mapper.MonthlyServiceUsingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(MonthlyServiceUsingResource.PATH)
public class MonthlyServiceUsingResource {
    public static final String PATH = "/api/monthlyServiceUsing";
    @Autowired
    private MonthlyServiceUsingServiceImpl monthlyServiceUsingService;
    @Autowired
    private ContractDealService contractDealService;

    @GetMapping
    public ResponseEntity<List<MonthlyServiceUsingDto>> getAll() {
        List<MonthlyServiceUsing> monthlyServiceUsingList = monthlyServiceUsingService.getAll();
        return ResponseEntity.ok(MonthlyServiceUsingMapper.INSTANCE.toServiceUsingDtos(monthlyServiceUsingList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyServiceUsingDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyServiceUsing monthlyServiceUsing = monthlyServiceUsingService.findById(id)
                .orElseThrow(ExceptionList::monthlyServiceUsingNotFound);
        return ResponseEntity.ok().body(MonthlyServiceUsingMapper.INSTANCE.toServiceUsingDto(monthlyServiceUsing));
    }

    @GetMapping("/moneyCollected")
    public ResponseEntity<List<MonthlyMoneyCollectedDto>> getMoneyCollectd(@RequestParam(name = "month", required = false) String month) throws ResourceNotFoundException {
        if (month != null) {
            List<MonthlyServiceUsing> monthlyServiceUsingList = monthlyServiceUsingService.findByMonth(Month.valueOf(month.toUpperCase()))
                    .orElseThrow(ExceptionList::monthlyServiceUsingNotFound);
            return ResponseEntity.ok(MonthlyServiceUsingMapper.INSTANCE.toMoneyCollectedDtos(monthlyServiceUsingList));
        }
        List<MonthlyServiceUsing> monthlyServiceUsingList = monthlyServiceUsingService.getAll();
        return ResponseEntity.ok(MonthlyServiceUsingMapper.INSTANCE.toMoneyCollectedDtos(monthlyServiceUsingList));
    }

    @PostMapping
    public ResponseEntity<MonthlyServiceUsingDto> create(@RequestBody MonthlyServiceUsingRequest monthlyServiceUsingRequest) throws ResourceNotFoundException {

        MonthlyServiceUsing createMonthlyServiceUsing = monthlyServiceUsingService.save(
                monthlyServiceUsingService.create(monthlyServiceUsingRequest)
        );
        return ResponseEntity
                .created(URI.create((MonthlyServiceUsingResource.PATH + "/" + createMonthlyServiceUsing.getId())))
                .body(MonthlyServiceUsingMapper.INSTANCE.toServiceUsingDto(createMonthlyServiceUsing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyServiceUsing monthlyServiceUsing = monthlyServiceUsingService.findById(id)
                .orElseThrow(ExceptionList::monthlyServiceUsingNotFound);
        monthlyServiceUsingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyServiceUsingDto> update(@PathVariable(value = "id") Integer monthlyServiceUsingId, @RequestBody MonthlyServiceUsingRequest monthlyServiceUsingRequest) throws ResourceNotFoundException {

        MonthlyServiceUsing monthlyServiceUsingUpdate = monthlyServiceUsingService.save(monthlyServiceUsingService.edit(monthlyServiceUsingId,monthlyServiceUsingRequest));

        return ResponseEntity.ok(MonthlyServiceUsingMapper.INSTANCE.toServiceUsingDto(monthlyServiceUsingUpdate));
    }


}