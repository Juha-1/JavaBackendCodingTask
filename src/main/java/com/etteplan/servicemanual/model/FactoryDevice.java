package com.etteplan.servicemanual.model;

import java.util.Map;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "factorydevices")
public class FactoryDevice {

    @Id
    private String _id;
    private String name;
    private int year;
    private String type;

    protected FactoryDevice() {
        // This is used when a new factory device is saved through rest post
    }

    /**
     * Constructor of FactoryDevice class
     * 
     * @param name Name of the device
     * @param year Year of the device
     * @param type Type of the device
     */
    public FactoryDevice(String name, int year, String type) {// Not used at the moment
        this.name = name;
        this.year = year;
        this.type = type;
    }

    public FactoryDevice(Map<String, String> variables) { // Object generator uses this constructor
        this.name = variables.get("Name");
        try {
            this.year = Integer.parseInt(variables.get("Year"));
        } catch (NumberFormatException e) {// Error in csv file, use year 0
            this.year = 0;
        }
        this.type = variables.get("Type");
    }

    public String get_id() {
        return this._id;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FactoryDevice_id=" + _id + ", name=" + name + ", year=" + year + ", type=" + type;
    }

}