package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.ContractDeal;
import com.axonactive.roomLeaseManagement.entity.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ContractDealRepository extends JpaRepository<ContractDeal,Integer> {
    Optional<ContractDeal> findByContractTenantPhoneNumber(String phoneNumber);
    Optional<ContractDeal> findByContractId(Integer id);

}
