package com.etteplan.servicemanual.controller;

class FactoryDeviceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    FactoryDeviceNotFoundException(String id) {
        super("Could not find factory device with id " + id);
    }
}