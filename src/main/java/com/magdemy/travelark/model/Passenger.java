package com.magdemy.travelark.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "PassengerInfo")
public class Passenger {
    @Id
    private String id;

    private String name;
    private String phone;
    private String password;
    private String rfid;
    private  String role;
    private List<List<String>> history = new ArrayList<>();

    public Passenger(String name, String phone, String password, String rfid, String role) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.rfid = rfid;
        this.role = role;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<List<String>> getHistory() {
        return history;
    }

    public void setHistory(List<List<String>> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", rfid='" + rfid + '\'' +
                ", role='" + role + '\'' +
                ", history='" + history + '\'' +
                '}';
    }
}

