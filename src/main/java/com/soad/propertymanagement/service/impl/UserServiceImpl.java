package com.soad.propertymanagement.service.impl;

import com.soad.propertymanagement.converter.UserConverter;
import com.soad.propertymanagement.dto.UserDTO;
import com.soad.propertymanagement.entity.UserEntity;
import com.soad.propertymanagement.exception.BusinessException;
import com.soad.propertymanagement.exception.ErrorModel;
import com.soad.propertymanagement.repository.UserRepository;
import com.soad.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());

        if (optionalUserEntity.isEmpty()) {
            UserEntity userEntity = userConverter.convertDTOTOEntity(userDTO);

            userEntity = userRepository.save(userEntity);

            userDTO = userConverter.convertEntityToDTO(userEntity);
            return userDTO;
        }
        else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXISTS");
            errorModel.setMessage(("The Email exists"));
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
    }

    @Override
    public UserDTO login(String email, String password) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        UserDTO userDTO = null;

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userDTO = userConverter.convertEntityToDTO(userEntity);
        }
        else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage(("Incorrect Email or Password"));
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }



















}
