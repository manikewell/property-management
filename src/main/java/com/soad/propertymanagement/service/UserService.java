package com.soad.propertymanagement.service;

import com.soad.propertymanagement.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO userDTO);
    UserDTO login(String email, String password);
}
