package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Relatives;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.RelativesRepository;
import com.axonactive.roomLeaseManagement.service.RelativesService;
import com.axonactive.roomLeaseManagement.service.dto.RelativesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class RelativesServiceImpl implements RelativesService {
    @Autowired
    private RelativesRepository relativesRepository;

    @Override
    public List<Relatives> getAll() {
        return relativesRepository.findAll();
    }

    @Override
    public Relatives save(Relatives relatives) {
        return relativesRepository.save(relatives);
    }

    @Override
    public Optional<Relatives> findById(Integer id) {
        return relativesRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        relativesRepository.deleteById(id);

    }

    @Override
    public Relatives create(RelativesDto relativesDto) {

        Relatives relatives = new Relatives();

        relatives.setFirstName(relativesDto.getFirstName());
        relatives.setLastName(relativesDto.getLastName());
        relatives.setPhoneNumber(relativesDto.getPhoneNumber());
        relatives.setIdCardNumber(relativesDto.getIdCardNumber());
        relatives.setRelationship(relativesDto.getRelationship());

        return relatives;
    }

    @Override
    public Relatives edit(Integer relativesId, RelativesDto relativesDto) {
        if(!relativesRepository.findById(relativesId).isPresent()){
            log.info("Cant find this relatives by id {} ", relativesId);
            throw ExceptionList.relativeNotFound();
        }

        Relatives relatives = relativesRepository.findById(relativesId).get();

        relatives.setFirstName(relativesDto.getFirstName());
        relatives.setLastName(relativesDto.getLastName());
        relatives.setPhoneNumber(relativesDto.getPhoneNumber());
        relatives.setIdCardNumber(relativesDto.getIdCardNumber());
        relatives.setRelationship(relativesDto.getRelationship());

        return relatives;
    }
}
