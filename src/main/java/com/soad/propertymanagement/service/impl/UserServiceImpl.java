package com.soad.propertymanagement.service.impl;

import com.soad.propertymanagement.converter.UserConverter;
import com.soad.propertymanagement.dto.UserDTO;
import com.soad.propertymanagement.entity.UserEntity;
import com.soad.propertymanagement.repository.UserRepository;
import com.soad.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTOTOEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
