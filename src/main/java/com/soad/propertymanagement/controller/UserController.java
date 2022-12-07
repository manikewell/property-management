package com.soad.propertymanagement.controller;

import com.soad.propertymanagement.dto.UserDTO;
import com.soad.propertymanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.register(userDTO);

        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.login(userDTO.getOwnerEmail(), userDTO.getPassword());

        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
        return responseEntity;
    }

}
