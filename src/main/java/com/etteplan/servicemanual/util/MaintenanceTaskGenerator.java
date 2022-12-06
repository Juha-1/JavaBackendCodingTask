package com.etteplan.servicemanual.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.etteplan.servicemanual.model.MaintenanceTask;

public class MaintenanceTaskGenerator {

    private static List<String> listOfFactoryDeviceIds;

    private static void delay() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException ie) {
        }
    }

    private static String pickRandomFactoryDeviceId() {
        return listOfFactoryDeviceIds.get(new Random().nextInt(listOfFactoryDeviceIds.size()));
    }

    private static MaintenanceTask.SeverityLevels getRandomSeverity() {
        MaintenanceTask.SeverityLevels[] values = MaintenanceTask.SeverityLevels.values();
        return values[new Random().nextInt(values.length)];
    }

    private static MaintenanceTask.StatusLevels getRandomStatus() {
        MaintenanceTask.StatusLevels[] values = MaintenanceTask.StatusLevels.values();
        return values[new Random().nextInt(values.length)];
    }

    public static List<MaintenanceTask> getGeneratedMaintenanceTasks(int number, List<String> factoryDeviceIdlist) {
        setListOfFactoryDeviceIds(factoryDeviceIdlist);

        List<MaintenanceTask> listOfTasks = new ArrayList<>();

        AtomicInteger index = new AtomicInteger();// index is used in forEach loop

        // heh :)
        IntStream.range(0, number).forEach(n -> {

            listOfTasks.add(new MaintenanceTask(pickRandomFactoryDeviceId(), getRandomSeverity(),
                    "Maintenance task " + (index.getAndIncrement() + 1), getRandomStatus()));
            delay();// This way we get different timestamps for MaintenanceTasks in object
                    // generation
        });

        return listOfTasks;
    }

    private static void setListOfFactoryDeviceIds(List<String> list) {
        listOfFactoryDeviceIds = list;
    }

}
