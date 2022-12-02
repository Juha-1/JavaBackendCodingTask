package com.etteplan.servicemanual.maintenancetask;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long> {
    
}