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
    private List<BusHistoryEntry> history;

    public Bus(String name, List<Double> location, List<BusHistoryEntry> history) {
        this.name = name;
        this.location = location;
        this.history = history;
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

    public List<BusHistoryEntry> getHistory() {
        return history;
    }

    public void setHistory(List<BusHistoryEntry> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", history=" + history +
                '}';
    }
}
