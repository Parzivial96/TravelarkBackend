package com.magdemy.travelark.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "BusInfo")
public class Bus {
    @Id
    private String id;

    private String name;
    private List<Double> location;
    private List<String> passengerIds;

    public Bus(String name, List<Double> location, List<String> passengerIds) {
        this.name = name;
        this.location = location;
        this.passengerIds = passengerIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public List<String> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(List<String> passengerIds) {
        this.passengerIds = passengerIds;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", passengerIds=" + passengerIds +
                '}';
    }
}
