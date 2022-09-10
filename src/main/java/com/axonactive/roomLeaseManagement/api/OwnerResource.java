package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Owner;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.service.Impl.OwnerServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.OwnerDto;
import com.axonactive.roomLeaseManagement.service.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(OwnerResource.PATH)
public class OwnerResource {
    public static final String PATH = "/api/owner";
    @Autowired
    private OwnerServiceImpl ownerService;


    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAll() {
        List<Owner> ownerList = ownerService.getAll();
        return ResponseEntity.ok(OwnerMapper.INSTANCE.toDtos(ownerList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable(value = "id") Integer id) {
        Owner owner = ownerService.findById(id)
                .orElseThrow(ExceptionList::ownerNotFound);
        return ResponseEntity.ok().body(OwnerMapper.INSTANCE.toDto(owner));
    }

    @PostMapping
    public ResponseEntity<OwnerDto> create(@RequestBody OwnerDto ownerDto) {

        Owner createOwner = ownerService.save(ownerService.create(ownerDto));

        return ResponseEntity.created(URI.create((OwnerResource.PATH + "/" + createOwner.getId()))).body(OwnerMapper.INSTANCE.toDto(createOwner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
        Owner owner = ownerService.findById(id)
                .orElseThrow(ExceptionList::ownerNotFound);
        ownerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDto> update(@PathVariable(value = "id") Integer ownerId, @RequestBody OwnerDto ownerDto){

        Owner ownerUpdate = ownerService.save(ownerService.edit(ownerId, ownerDto));

        return ResponseEntity.ok(OwnerMapper.INSTANCE.toDto(ownerUpdate));
    }


}
