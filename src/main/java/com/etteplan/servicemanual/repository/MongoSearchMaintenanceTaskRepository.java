package com.etteplan.servicemanual.repository;

import java.util.List;

import com.etteplan.servicemanual.model.MaintenanceTask;

public interface MongoSearchMaintenanceTaskRepository {
    List<MaintenanceTask> findByDescriptionText(String text);
}
