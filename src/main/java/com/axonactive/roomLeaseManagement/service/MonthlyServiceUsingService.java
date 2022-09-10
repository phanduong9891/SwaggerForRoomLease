package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.request.MonthlyServiceUsingRequest;

import java.util.List;
import java.util.Optional;

public interface MonthlyServiceUsingService {
    List<MonthlyServiceUsing> getAll();
    MonthlyServiceUsing save(MonthlyServiceUsing monthlyServiceUsing);
    Optional<MonthlyServiceUsing> findById(Integer id);
    void deleteById(Integer id);
    Optional<List<MonthlyServiceUsing>> findByMonth(Month month);
    MonthlyServiceUsing create(MonthlyServiceUsingRequest monthlyServiceUsingRequest);
    MonthlyServiceUsing edit(Integer monthlyServiceUsingId,MonthlyServiceUsingRequest monthlyServiceUsingRequest);

}
