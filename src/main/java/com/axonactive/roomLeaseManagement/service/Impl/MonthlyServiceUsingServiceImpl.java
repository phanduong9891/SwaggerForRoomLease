package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.MonthlyServiceUsingRepository;
import com.axonactive.roomLeaseManagement.request.MonthlyServiceUsingRequest;
import com.axonactive.roomLeaseManagement.service.MonthlyServiceUsingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class MonthlyServiceUsingServiceImpl implements MonthlyServiceUsingService {
    @Autowired
    private MonthlyServiceUsingRepository monthlyServiceUsingRepository;
    @Autowired
    private ContractDealServiceImpl contractDealService;

    @Override
    public List<MonthlyServiceUsing> getAll() {
        return monthlyServiceUsingRepository.findAll();
    }

    @Override
    public MonthlyServiceUsing save(MonthlyServiceUsing monthlyServiceUsing) {
        return monthlyServiceUsingRepository.save(monthlyServiceUsing);
    }

    @Override
    public Optional<MonthlyServiceUsing> findById(Integer id) {
        return monthlyServiceUsingRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        monthlyServiceUsingRepository.deleteById(id);
    }

    @Override
    public Optional<List<MonthlyServiceUsing>> findByMonth(Month month) {
        return monthlyServiceUsingRepository.findByMonth(month);
    }

    @Override
    public MonthlyServiceUsing create(MonthlyServiceUsingRequest monthlyServiceUsingRequest) {
        if(!contractDealService.findById(monthlyServiceUsingRequest.getContractDealId()).isPresent()){
            log.info("cant find contractDeal of monthlyServiceUsing by id {} ", monthlyServiceUsingRequest.getContractDealId());
            throw ExceptionList.contractDealNotFound();
        }
        MonthlyServiceUsing monthlyServiceUsing = new MonthlyServiceUsing();

        monthlyServiceUsing.setMonth(monthlyServiceUsingRequest.getMonth());
        monthlyServiceUsing.setYear(monthlyServiceUsing.getYear());
        monthlyServiceUsing.setElectricityUsage(monthlyServiceUsingRequest.getElectricityUsage());
        monthlyServiceUsing.setContractDeal(contractDealService.findById(monthlyServiceUsingRequest.getContractDealId()).get());

        return monthlyServiceUsing;
    }

    @Override
    public MonthlyServiceUsing edit(Integer monthlyServiceUsingId, MonthlyServiceUsingRequest monthlyServiceUsingRequest) {
        if(!monthlyServiceUsingRepository.findById(monthlyServiceUsingId).isPresent()){
            log.info("Cant find monthlyServiceUsing by id {} ", monthlyServiceUsingId);
            throw ExceptionList.monthlyServiceUsingNotFound();
        }
        if(!contractDealService.findById(monthlyServiceUsingRequest.getContractDealId()).isPresent()){
            log.info("cant find contractDeal of monthlyServiceUsing by id {} ", monthlyServiceUsingRequest.getContractDealId());
            throw ExceptionList.contractDealNotFound();
        }
        MonthlyServiceUsing editMonthlyServiceUsing = monthlyServiceUsingRepository.findById(monthlyServiceUsingId).get();

        editMonthlyServiceUsing.setMonth(monthlyServiceUsingRequest.getMonth());
        editMonthlyServiceUsing.setYear(monthlyServiceUsingRequest.getYear());
        editMonthlyServiceUsing.setElectricityUsage(monthlyServiceUsingRequest.getElectricityUsage());
        editMonthlyServiceUsing.setContractDeal(contractDealService.findById(monthlyServiceUsingRequest.getContractDealId()).get());

        return editMonthlyServiceUsing;
    }

}
