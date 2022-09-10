package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.entity.Relatives;
import com.axonactive.roomLeaseManagement.service.dto.RelativesDto;

import java.util.List;
import java.util.Optional;

public interface RelativesService {
    List<Relatives> getAll();
    Relatives save(Relatives relatives);
    Optional<Relatives> findById(Integer id);
    void deleteById(Integer id);
    Relatives create(RelativesDto relativesDto);
    Relatives edit(Integer relativesId, RelativesDto relativesDto);
}
