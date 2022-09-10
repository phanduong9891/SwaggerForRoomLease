package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlyServiceUsingRepository extends JpaRepository<MonthlyServiceUsing,Integer> {
    Optional<List<MonthlyServiceUsing>> findByMonth(Month month);

}
