package com.etteplan.servicemanual;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.etteplan.servicemanual.model.FactoryDevice;
import com.etteplan.servicemanual.model.MaintenanceTask;
import com.etteplan.servicemanual.repository.FactoryDeviceRepository;
import com.etteplan.servicemanual.repository.MaintenanceTaskRepository;
import com.etteplan.servicemanual.util.CSVReader;
import com.etteplan.servicemanual.util.MaintenanceTaskGenerator;

@SpringBootApplication

public class ServiceManualApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ServiceManualApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final FactoryDeviceRepository factoryDeviceRepository,
            final MaintenanceTaskRepository maintenanceTaskRepository) {
        return (args) -> {

            List<FactoryDevice> savedDevices = factoryDeviceRepository.findAll();

            if (savedDevices.isEmpty()) { //If there is no data in database, generate some.

                List<FactoryDevice> devices = new ArrayList<FactoryDevice>();

                if (CSVReader.getValuelistFromCSVFile("seeddata.csv")) { //Use helper class to read csv

                    CSVReader.getValues().forEach(line -> {
                        devices.add(new FactoryDevice(line));
                    });

                    factoryDeviceRepository.saveAll(devices); // Save devices to mongo

                    savedDevices = factoryDeviceRepository.findAll(); // Read devices from Mongo with
                                                                      // Mongo generated id
                                                                      
                    // Generate and save tasks to Mongo
                    maintenanceTaskRepository.saveAll(MaintenanceTaskGenerator.getGeneratedMaintenanceTasks(100,
                            savedDevices.stream().map(dev -> dev.get_id()).collect(Collectors.toList())));

                } else {
                    System.out.println("CSV Reading failed with error message: " + CSVReader.getError());
                }

            }

            // Print out what we have
            final List<MaintenanceTask> savedTasks = maintenanceTaskRepository.findAll();
            savedTasks.forEach(
                    task -> System.out.println(task.toString()));

        };
    }

}