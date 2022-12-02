package com.etteplan.servicemanual.maintenancetask;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class MaintenanceTask {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;    
    private Long factoryDeviceId;    
    private int severity;    
    private String description;
    private int status;
    private Long timeStamp;

    protected MaintenanceTask() {}

    /**
     * Constructor of MaintenanceTask class
     * 
     * @param factoryDeviceId
     * @param severity
     * @param description
     * @param status     
     */
    //public MaintenanceTask(Long factoryDeviceId, int severity, String description, int status) {
    public MaintenanceTask(Long factoryDeviceId, int severity, String description, int status) {
        this.factoryDeviceId = factoryDeviceId;
        this.severity = severity;
        this.description = description;
        this.status = status;                   
        this.timeStamp = new Date().getTime();              
    }

    public Long getId() {
        return this.id;
    }

    /**
     * 
     * @return
     */
    public Long getFactoryDeviceId() {
        return this.factoryDeviceId;
    }

    public int getSeverity() {
        return this.severity;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStatus() {
        return this.status;
    }

    public Long getTimeStamp() {
        return this.timeStamp;
    }

    public void setFactoryDeviceId(Long factoryDeviceId) {
        this.factoryDeviceId = factoryDeviceId;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimeStamp(Long timestamp) {
        this.timeStamp = timestamp;
    }
}
