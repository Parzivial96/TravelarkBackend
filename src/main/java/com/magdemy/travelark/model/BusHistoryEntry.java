package com.magdemy.travelark.model;

import java.util.List;

public class BusHistoryEntry {
    private String date;
    private String driverId;
    private String driverName;
    private List<String> passengerIds;

    // Constructors
    public BusHistoryEntry() {
    }

    public BusHistoryEntry(String date, String driverId, String driverName, List<String> passengerIds) {
        this.date = date;
        this.driverId = driverId;
        this.driverName = driverName;
        this.passengerIds = passengerIds;
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public List<String> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(List<String> passengerIds) {
        this.passengerIds = passengerIds;
    }

    // toString() method
    @Override
    public String toString() {
        return "HistoryEntry{" +
                "date='" + date + '\'' +
                ", driverId='" + driverId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", passengerIds=" + passengerIds +
                '}';
    }
}
