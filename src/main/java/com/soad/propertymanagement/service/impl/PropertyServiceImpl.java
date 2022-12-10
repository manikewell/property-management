package com.soad.propertymanagement.service.impl;

import com.soad.propertymanagement.converter.PropertyConverter;
import com.soad.propertymanagement.dto.PropertyDTO;
import com.soad.propertymanagement.entity.PropertyEntity;
import com.soad.propertymanagement.repository.PropertyRepository;
import com.soad.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Value("${pms.dummy:}") //having colon after property will prevent NPE if they do not exist in property file
    private String dummy;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyConverter.convertDTOToEntity(propertyDTO);
        propertyEntity = propertyRepository.save(propertyEntity);

        PropertyDTO dto = propertyConverter.covertEntityToDTO(propertyEntity);
        return dto;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        System.out.println("service dummy:"+dummy);
        System.out.println("service dummy:"+dummy);

        List<PropertyEntity> propertyList = (List<PropertyEntity>)propertyRepository.findAll();
        List<PropertyDTO> dtoList = new ArrayList<>();
        for (PropertyEntity pe:propertyList) {
            PropertyDTO dto = propertyConverter.covertEntityToDTO(pe);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optionalEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalEntity.get();
            propertyEntity.setAddress(propertyDTO.getAddress());
            propertyEntity.setPrice(propertyDTO.getPrice());
            propertyEntity.setDescription(propertyDTO.getDescription());
            propertyEntity.setTitle(propertyDTO.getTitle());

            propertyEntity = propertyRepository.save(propertyEntity);
            dto = propertyConverter.covertEntityToDTO(propertyEntity);
        }
        return dto;
    }

    @Override
    public PropertyDTO updateDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optionalEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalEntity.get();
            propertyEntity.setDescription(propertyDTO.getDescription());

            propertyEntity = propertyRepository.save(propertyEntity);
            dto = propertyConverter.covertEntityToDTO(propertyEntity);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePrice(PropertyDTO propertyDTO, Long propertyId) {
        return null;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }


}
