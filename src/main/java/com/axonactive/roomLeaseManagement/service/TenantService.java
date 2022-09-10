package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Tenant;
import com.axonactive.roomLeaseManagement.request.TenantRequest;
import com.axonactive.roomLeaseManagement.service.dto.TenantMonthsRentDto;

import java.util.List;
import java.util.Optional;

public interface TenantService {
    List<Tenant> getAll();
    Tenant save(Tenant tenant);
    Optional<Tenant> findById(Integer id);
    void deleteById(Integer id);
    Optional<Tenant> findByPhoneNumber(String phoneNumber);
    Optional<Tenant> findByIdCardNumber(String idCardNumber);
    List<TenantMonthsRentDto> tenantMonthRent();
    Tenant create(TenantRequest tenantRequest);
    Tenant edit(Integer tenatId, TenantRequest tenantRequest);
}
