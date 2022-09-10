package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Tenant;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.TenantRepository;
import com.axonactive.roomLeaseManagement.request.TenantRequest;
import com.axonactive.roomLeaseManagement.service.TenantService;
import com.axonactive.roomLeaseManagement.service.dto.TenantMonthsRentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private RelativesServiceImpl relativesService;

    @Override
    public List<Tenant> getAll() {
        return tenantRepository.findAll();
    }

    @Override
    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public Optional<Tenant> findById(Integer id) {
        return tenantRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        tenantRepository.deleteById(id);

    }

    @Override
    public Optional<Tenant> findByPhoneNumber(String phoneNumber) {
        return tenantRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Tenant> findByIdCardNumber(String idCardNumber) {
        return tenantRepository.findByIdCardNumber(idCardNumber);
    }

    @Override
    public List<TenantMonthsRentDto> tenantMonthRent() {
        return tenantRepository.tenantMonthRent();
    }

    @Override
    public Tenant create(TenantRequest tenantRequest) {
        if (!relativesService.findById(tenantRequest.getRelativesId()).isPresent()) {
            log.info("Cant find relative of this tenant by this id {} ", tenantRequest.getRelativesId());
            throw ExceptionList.relativeNotFound();
        }

        Tenant tenant = new Tenant();

        tenant.setFirstName(tenantRequest.getFirstName());
        tenant.setLastName(tenantRequest.getLastName());
        tenant.setIdCardNumber(tenantRequest.getIdCardNumber());
        tenant.setPhoneNumber(tenantRequest.getPhoneNumber());
        tenant.setGender(tenantRequest.getGender());
        tenant.setRelatives(relativesService.findById(tenantRequest.getRelativesId()).get());
        tenant.setBirthday(tenantRequest.getBirthday());

        return tenant;
    }

    @Override
    public Tenant edit(Integer tenantId, TenantRequest tenantRequest) {
        if (!tenantRepository.findById(tenantId).isPresent()) {
            log.info("Cant find tenant with this id {} ", tenantId);
            throw ExceptionList.tenantNotFound();
        }
        if (!relativesService.findById(tenantRequest.getRelativesId()).isPresent()) {
            log.info("Cant find relative of this tenant by this id {} ", tenantRequest.getRelativesId());
            throw ExceptionList.relativeNotFound();
        }

        Tenant tenant = tenantRepository.findById(tenantId).get();

        tenant.setFirstName(tenantRequest.getFirstName());
        tenant.setLastName(tenantRequest.getLastName());
        tenant.setIdCardNumber(tenantRequest.getIdCardNumber());
        tenant.setPhoneNumber(tenantRequest.getPhoneNumber());
        tenant.setGender(tenantRequest.getGender());
        tenant.setRelatives(relativesService.findById(tenantRequest.getRelativesId()).get());
        tenant.setBirthday(tenantRequest.getBirthday());

        return tenant;
    }


}