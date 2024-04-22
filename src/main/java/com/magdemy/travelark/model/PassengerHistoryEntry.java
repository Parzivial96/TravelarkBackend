package com.magdemy.travelark.model;

public class PassengerHistoryEntry {
    private String date;
    private String latitude;
    private String longitude;
    private String intime;
    private String droptime;
    private String dropLatitude;
    private  String dropLongitude;

    public PassengerHistoryEntry(String date, String latitude, String longitude, String intime, String droptime, String dropLatitude, String dropLongitude) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.intime = intime;
        this.droptime = droptime;
        this.dropLatitude = dropLatitude;
        this.dropLongitude = dropLongitude;
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


    public String getDropLatitude() {
        return dropLatitude;
    }

    public String getDropLongitude() {
        return dropLongitude;
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

    public void setDropLatitude(String dropLatitude) {
        this.dropLatitude = dropLatitude;
    }

    public void setDropLongitude(String dropLongitude) {
        this.dropLongitude = dropLongitude;
    }

    @Override
    public String toString() {
        return "PassengerHistoryEntry{" +
                "date='" + date + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", intime='" + intime + '\'' +
                ", droptime='" + droptime + '\'' +
                ", dropLatitude='" + dropLatitude + '\'' +
                ", dropLongitude='" + dropLongitude + '\'' +
                '}';
    }
}
