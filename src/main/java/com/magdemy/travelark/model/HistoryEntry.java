package com.magdemy.travelark.model;

import java.util.List;

public class HistoryEntry {
    private String date;
    private String driverId;
    private String driverName;
    private String startTime;
    private String endTime;
    private List<String> passengerIds;

    // Constructors
    public HistoryEntry() {
    }

    public HistoryEntry(String date, String driverId, String driverName, String startTime, String endTime, List<String> passengerIds) {
        this.date = date;
        this.driverId = driverId;
        this.driverName = driverName;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", passengerIds=" + passengerIds +
                '}';
    }
}
