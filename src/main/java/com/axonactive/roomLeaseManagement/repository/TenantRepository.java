package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.Tenant;
import com.axonactive.roomLeaseManagement.service.dto.TenantMonthsRentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Integer> {
    Optional<Tenant> findByPhoneNumber(String phoneNumber);
    Optional<Tenant> findByIdCardNumber(String idCardNumber);
    @Query("SELECT new com.axonactive.roomLeaseManagement.service.dto.TenantMonthsRentDto((t.lastName || ' ' || t.firstName) AS fullName, COUNT(m.contract.id)) " +
            "FROM MonthlyPayment m, Contract c, Tenant t " +
            "WHERE m.contract.id = c.id AND c.tenant.id = t.id "+
            "GROUP BY m.contract.id, fullName "+
            "ORDER BY m.contract.id")
    List<TenantMonthsRentDto> tenantMonthRent();

}
