package com.etteplan.servicemanual.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.etteplan.servicemanual.model.MaintenanceTask;
import com.etteplan.servicemanual.repository.FactoryDeviceRepository;
import com.etteplan.servicemanual.repository.MaintenanceTaskRepository;

@RestController
public class MaintenanceTaskController {

    @Autowired
    MaintenanceTaskRepository maintenanceTaskRepository;

    @Autowired
    FactoryDeviceRepository factoryDeviceRepository;

    @GetMapping("/maintenancetasks")
    List<MaintenanceTask> all() {
        return maintenanceTaskRepository
                .findAll(Sort.by(Sort.Direction.ASC, "severity").and(Sort.by(Sort.Direction.ASC, "timestamp")));
    }

    @GetMapping("/maintenancetasks/{id}")
    MaintenanceTask one(@PathVariable String id) {
        return maintenanceTaskRepository.findById(id)
                .orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
    }

    @PostMapping("/maintenancetasks")
    MaintenanceTask newMaintenanceTask(@RequestBody MaintenanceTask newMaintenanceTask) {

        // Let's see if provided factory device exists
        factoryDeviceRepository.findById(newMaintenanceTask.getFactoryDeviceId())
                .orElseThrow(() -> new FactoryDeviceNotFoundException(newMaintenanceTask.getFactoryDeviceId()));

        return maintenanceTaskRepository.save(newMaintenanceTask);
    }

    @PostMapping("/maintenancetasks/search")
    List<MaintenanceTask> maintenanceTaskSearch(@RequestBody Map<String, String> body) {

        return maintenanceTaskRepository.findByFactoryDeviceId(body.get("factoryDeviceId"));

    }

    @PutMapping("/maintenancetasks/{id}")
    MaintenanceTask replaceMaintenanceTask(@RequestBody MaintenanceTask newMaintenanceTask, @PathVariable String id) {

        // Let's see if provided factory device exists
        factoryDeviceRepository.findById(newMaintenanceTask.getFactoryDeviceId())
                .orElseThrow(() -> new FactoryDeviceNotFoundException(newMaintenanceTask.getFactoryDeviceId()));

        return maintenanceTaskRepository.findById(id)
                .map(maintenanceTask -> {
                    maintenanceTask.setFactoryDeviceId(newMaintenanceTask.getFactoryDeviceId());
                    maintenanceTask.setSeverity(newMaintenanceTask.getSeverity());
                    maintenanceTask.setStatus(newMaintenanceTask.getStatus());
                    maintenanceTask.setDescription(newMaintenanceTask.getDescription());
                    return maintenanceTaskRepository.save(maintenanceTask);
                })
                .orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
    }

    @DeleteMapping("/maintenancetasks/{id}")
    void deleteMaintenanceTask(@PathVariable String id) {
        maintenanceTaskRepository.deleteById(id);
    }
}
