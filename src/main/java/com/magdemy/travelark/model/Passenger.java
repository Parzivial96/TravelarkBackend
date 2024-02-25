package com.magdemy.travelark.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PassengerInfo")
public class Passenger {
    @Id
    private String id;

    private String name;
    private String stopping;
    private String rfid;

    public Passenger(String name, String stopping, String rfid) {
        this.name = name;
        this.stopping = stopping;
        this.rfid = rfid;
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

    public String getStopping() {
        return stopping;
    }

    public void setStopping(String stopping) {
        this.stopping = stopping;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stopping='" + stopping + '\'' +
                ", rfid='" + rfid + '\'' +
                '}';
    }
}

