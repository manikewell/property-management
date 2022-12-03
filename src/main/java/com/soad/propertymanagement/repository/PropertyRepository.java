package com.soad.propertymanagement.repository;

import com.soad.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}
