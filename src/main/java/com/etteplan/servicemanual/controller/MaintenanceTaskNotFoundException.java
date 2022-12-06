package com.etteplan.servicemanual.controller;

class MaintenanceTaskNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    MaintenanceTaskNotFoundException(String id) {
        super("Could not find maintenance task " + id);
    }
}