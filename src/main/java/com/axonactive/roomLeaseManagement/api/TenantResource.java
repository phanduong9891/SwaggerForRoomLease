package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Tenant;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.TenantRequest;
import com.axonactive.roomLeaseManagement.service.Impl.RelativesServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.TenantServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.TenantDto;
import com.axonactive.roomLeaseManagement.service.dto.TenantMonthsRentDto;
import com.axonactive.roomLeaseManagement.service.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(TenantResource.PATH)
public class TenantResource {
    public static final String PATH = "/api/tenant";
    @Autowired
    private TenantServiceImpl tenantService;
    @Autowired
    private RelativesServiceImpl relativesService;

    @GetMapping
    public ResponseEntity<List<TenantDto>> getAll() {
        List<Tenant> tenantList = tenantService.getAll();
        return ResponseEntity.ok(TenantMapper.INSTANCE.toDtos(tenantList));
    }

    @GetMapping("/search")
    public ResponseEntity<TenantDto> searchBy(@RequestParam(name = "phoneNumber", required = false) String phoneNumber, @RequestParam(name = "idCardNumber", required = false) String idCardNumber) throws ResourceNotFoundException {
        if (null == idCardNumber) {
            Tenant tenant = tenantService.findByPhoneNumber(phoneNumber)
                    .orElseThrow(ExceptionList::tenantNotFound);
            return ResponseEntity.ok().body(TenantMapper.INSTANCE.toDto(tenant));
        }
        Tenant tenant = tenantService.findByIdCardNumber(idCardNumber)
                .orElseThrow(ExceptionList::tenantNotFound);
        return ResponseEntity.ok().body(TenantMapper.INSTANCE.toDto(tenant));
    }

    @GetMapping("/tenantMonthRented")
    public ResponseEntity<List<TenantMonthsRentDto>> getTenantMonthRented() {
        return ResponseEntity.ok(tenantService.tenantMonthRent());
    }

    @PostMapping
    public ResponseEntity<TenantDto> create(@RequestBody TenantRequest tenantRequest) throws ResourceNotFoundException {
        Tenant createTenant = tenantService.save(tenantService.create(tenantRequest));

        return ResponseEntity.created(URI.create((TenantResource.PATH + "/" + createTenant.getId()))).body(TenantMapper.INSTANCE.toDto(createTenant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Tenant tenant = tenantService.findById(id)
                .orElseThrow(ExceptionList::tenantNotFound);
        tenantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantDto> update(@PathVariable(value = "id") Integer tenantId, @RequestBody TenantRequest tenantRequest) throws ResourceNotFoundException {
        Tenant tenantUpdate = tenantService.save(tenantService.edit(tenantId,tenantRequest));

        return ResponseEntity.ok(TenantMapper.INSTANCE.toDto(tenantUpdate));
    }


}
