package com.axonactive.roomLeaseManagement.security.service.impl;

import com.axonactive.roomLeaseManagement.security.mapper.UserMapper;
import com.axonactive.roomLeaseManagement.security.repository.UserRepository;
import com.axonactive.roomLeaseManagement.security.service.UserService;
import com.axonactive.roomLeaseManagement.security.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<UserDTO> getUsers() {
        return UserMapper.INSTANCE.mapToDtos(userRepository.findAll());

    }
}
