package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Owner;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.OwnerRepository;
import com.axonactive.roomLeaseManagement.service.OwnerService;
import com.axonactive.roomLeaseManagement.service.dto.OwnerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private final OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Optional<Owner> findById(Integer id) {
        return ownerRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner create(OwnerDto ownerDto) {
        Owner owner = new Owner();

        owner.setFirstName(ownerDto.getFirstName());
        owner.setLastName(ownerDto.getLastName());
        owner.setGender(ownerDto.getGender());
        owner.setEmail(ownerDto.getEmail());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());

        return owner;
    }

    @Override
    public Owner edit(Integer ownerId, OwnerDto ownerDto) {
        if(!ownerRepository.findById(ownerId).isPresent()){
            log.info("Cant find owner with this id {} ", ownerId);
            throw ExceptionList.ownerNotFound();
        }
        Owner owner = ownerRepository.findById(ownerId).get();

        owner.setFirstName(ownerDto.getLastName());
        owner.setLastName(ownerDto.getLastName());
        owner.setGender(ownerDto.getGender());
        owner.setEmail(ownerDto.getEmail());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());

        return owner;
    }
}