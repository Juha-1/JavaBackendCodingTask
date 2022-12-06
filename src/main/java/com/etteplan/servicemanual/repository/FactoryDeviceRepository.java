package com.etteplan.servicemanual.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.etteplan.servicemanual.model.FactoryDevice;

public interface FactoryDeviceRepository extends MongoRepository<FactoryDevice, String> {

}