package com.etteplan.servicemanual;

import java.util.*;
import java.io.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.etteplan.servicemanual.factorydevice.FactoryDevice;
import com.etteplan.servicemanual.factorydevice.FactoryDeviceRepository;

import com.etteplan.servicemanual.maintenancetask.MaintenanceTask;
import com.etteplan.servicemanual.maintenancetask.MaintenanceTaskRepository;

@SpringBootApplication
public class ServiceManualApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ServiceManualApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final FactoryDeviceRepository factoryDeviceRepository, final MaintenanceTaskRepository maintenanceTaskRepository) {
        return (args) -> {
            final List<FactoryDevice> devices = new ArrayList<FactoryDevice>();

            //Read CSV file
            Scanner scanner = new Scanner(new File("seeddata.csv"));
            scanner.useDelimiter(",");
            
            //Read csv heade, it's not used.
            scanner.nextLine();
            //Read through the file.
            while(scanner.hasNext()){
                //Split line data to table.
                String[] csvline = scanner.nextLine().split(",");
                //Create new objects with the csv line data. Possible csv fli errors are not checked throughoutly.
                //Just simple check for number format, if error in csv. We skip the line with error but continue reading.
                try{
                    devices.add(new FactoryDevice(csvline[0], Integer.parseInt(csvline[1]), csvline[2]));
                }catch(NumberFormatException e){
                    System.out.println("Error in csv file, skipping line");
                }
                
            }
            scanner.close();

            factoryDeviceRepository.saveAll(devices);            

            //MaintenanceTask task = new MaintenanceTask(2, "Testing", 0);

            final List<MaintenanceTask> tasks = Arrays.asList(                
                new MaintenanceTask(Long.valueOf(1),2, "Testing 1", 0),
                new MaintenanceTask(Long.valueOf(2),1, "Testing 2", 1),
                new MaintenanceTask(Long.valueOf(3),3, "Testing 3", 0)
            );

            
            maintenanceTaskRepository.saveAll(tasks);

            /*tasks.forEach(task -> {
                System.out.println(task.getDescription());
            });   */        



            /**
             * Remove this. Temporary device storage before proper data storage is implemented.
             */
            /* 
            final List<FactoryDevice> devices = Arrays.asList(
                new FactoryDevice("Device X", 2001, "type 10"),
                new FactoryDevice("Device Y", 2012, "type 3"),
                new FactoryDevice("Device Z", 1985, "type 1")
            );*/

            
        };
    }

}