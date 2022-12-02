package com.etteplan.servicemanual.maintenancetask;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MaintenanceTaskController {

    private final MaintenanceTaskRepository repository;

    MaintenanceTaskController(MaintenanceTaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/maintenancetasks")
    List<MaintenanceTask> all() {
        return repository.findAll();
    }

    @GetMapping("/maintenancetasks/{id}")
    MaintenanceTask one(@PathVariable Long id) {        
        return repository.findById(id)
            .orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
    }

    @PostMapping("/maintenancetasks")
    MaintenanceTask newMaintenanceTask(@RequestBody MaintenanceTask newMaintenanceTask) {
        return repository.save(newMaintenanceTask);
    }

    @PostMapping("/maintenancetasks/search")
    List<MaintenanceTask> maintenanceTaskSearch(@RequestParam Map<String,String> allParams) {
        System.out.println("Parameters " + allParams.entrySet());
        return repository.findAll();
    }

    @PutMapping("/maintenancetasks/{id}")
    MaintenanceTask replaceMaintenanceTask(@RequestBody MaintenanceTask newMaintenanceTask, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(maintenanceTask -> {
        maintenanceTask.setFactoryDeviceId(newMaintenanceTask.getFactoryDeviceId());
        maintenanceTask.setSeverity(newMaintenanceTask.getSeverity());
        return repository.save(maintenanceTask);
      })
      .orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
    }

    @DeleteMapping("/maintenancetasks/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


