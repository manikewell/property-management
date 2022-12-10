package com.soad.propertymanagement.converter;

import com.soad.propertymanagement.dto.UserDTO;
import com.soad.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity convertDTOTOEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setOwnerEmail(userDTO.getOwnerEmail());
        userEntity.setOwnerName(userDTO.getOwnerName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setPhone(userDTO.getPhone());

        return userEntity;
    }

    public UserDTO convertEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        userDTO.setOwnerName(userEntity.getOwnerName());
        //userDTO.setPassword(userEntity.getPassword());
        userDTO.setPhone(userEntity.getPhone());

        return userDTO;
    }
}
