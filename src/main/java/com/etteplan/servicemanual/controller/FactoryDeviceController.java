package com.etteplan.servicemanual.controller;

import org.springframework.web.bind.annotation.RestController;

import com.etteplan.servicemanual.model.FactoryDevice;
import com.etteplan.servicemanual.repository.FactoryDeviceRepository;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class FactoryDeviceController {

    private final FactoryDeviceRepository repository;

    FactoryDeviceController(FactoryDeviceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/factorydevices")
    List<FactoryDevice> all() {
        return repository.findAll();
    }

    @GetMapping("/factorydevices/{id}")
    FactoryDevice one(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new FactoryDeviceNotFoundException(id));
    }

    @PostMapping("/factorydevices")
    FactoryDevice newMaintenanceTask(@RequestBody FactoryDevice newFactoryDevice) {

        return repository.save(newFactoryDevice);
    }

    @PutMapping("/factorydevices/{id}")
    FactoryDevice replaceMaintenanceTask(@RequestBody FactoryDevice newFactoryDevice, @PathVariable String id) {

        return repository.findById(id)
                .map(factoryDevice -> {
                    factoryDevice.setName(newFactoryDevice.getName());
                    factoryDevice.setYear(newFactoryDevice.getYear());
                    factoryDevice.setType(newFactoryDevice.getType());
                    return repository.save(factoryDevice);
                })
                .orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
    }

    @DeleteMapping("/factorydevices/{id}")
    void deleteFactoryDevice(@PathVariable String id) {
        repository.deleteById(id);
    }
}