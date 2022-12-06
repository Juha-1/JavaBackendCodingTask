package com.etteplan.servicemanual.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "maintenancetasks")
public class MaintenanceTask {

    @Id
    private String _id;
    private String factoryDeviceId;
    @Enumerated(EnumType.ORDINAL)
    private SeverityLevels severity;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private StatusLevels status;
    private Long timestamp;

    public enum SeverityLevels {
        CRITICAL,
        IMPORTANT,
        UNINPORTANT
    }

    public enum StatusLevels {
        OPEN,
        CLOSED
    }

    protected MaintenanceTask() {
        // This is needed to generate timestamp when adding a new MaintenanceTask from
        // rest post call
        this.timestamp = new Date().getTime();
    }

    /**
     * Constructor of MaintenanceTask class
     * 
     * @param factoryDeviceId
     * @param severity
     * @param description
     * @param status
     */

    public MaintenanceTask(String factoryDeviceId, SeverityLevels severity, String description, StatusLevels status) {
        this.factoryDeviceId = factoryDeviceId;
        this.severity = severity;
        this.description = description;
        this.status = status;
        this.timestamp = new Date().getTime();
    }

    public String getId() {
        return this._id;
    }

    /**
     * 
     * @return
     */
    public String getFactoryDeviceId() {
        return this.factoryDeviceId;
    }

    public SeverityLevels getSeverity() {
        return this.severity;
    }

    public String getDescription() {
        return this.description;
    }

    public StatusLevels getStatus() {
        return this.status;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setFactoryDeviceId(String factoryDeviceId) {
        this.factoryDeviceId = factoryDeviceId;
    }

    public void setSeverity(SeverityLevels severity) {
        this.severity = severity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(StatusLevels status) {
        this.status = status;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MaintenanceTaskid=" + _id + ", factoryDeviceId=" + factoryDeviceId + ", severity=" + severity
                + ", description=" + description + ", status=" + status + ", timestamp=" + timestamp;
    }

}
