package com.etteplan.servicemanual.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.etteplan.servicemanual.model.MaintenanceTask;

public interface MaintenanceTaskRepository extends MongoRepository<MaintenanceTask, String> {

    List<MaintenanceTask> findByFactoryDeviceId(String id);

}