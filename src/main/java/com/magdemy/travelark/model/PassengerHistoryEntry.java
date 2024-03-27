package com.magdemy.travelark.model;

public class PassengerHistoryEntry {
    private String date;
    private String latitude;
    private String longitude;
    private String intime;
    private String droptime;

    public PassengerHistoryEntry(String date, String latitude, String longitude, String intime, String droptime) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.intime = intime;
        this.droptime = droptime;
    }

    public PassengerHistoryEntry() {

    }

    public String getDate() {
        return date;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getIntime() {
        return intime;
    }

    public String getDroptime() {
        return droptime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public void setDroptime(String droptime) {
        this.droptime = droptime;
    }

    @Override
    public String toString() {
        return "PassengerHistoryEntry{" +
                "date='" + date + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", intime='" + intime + '\'' +
                ", droptime='" + droptime + '\'' +
                '}';
    }
}
