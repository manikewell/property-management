package com.soad.propertymanagement.controller;

import com.soad.propertymanagement.dto.PropertyDTO;
import com.soad.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PropertyController {

    @Value("${pms.dummy:}") //having colon after property will prevent NPE if they do not exist in property file
    private String dummy;
    @Autowired
    private PropertyService propertyService;
    //RESTful API
    @GetMapping("/hello")
    public String sayHello() {

        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO dto = propertyService.saveProperty(propertyDTO);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        System.out.println("dummy:"+dummy);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(propertyList, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId) {
        PropertyDTO dto = propertyService.updateDescription(propertyDTO, propertyId);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/properties/delete-property/{propertyId}")
    public ResponseEntity deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        ResponseEntity responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        return responseEntity;
    }
























}
