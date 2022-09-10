package com.axonactive.roomLeaseManagement.security.service;

import com.axonactive.roomLeaseManagement.security.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();
}
