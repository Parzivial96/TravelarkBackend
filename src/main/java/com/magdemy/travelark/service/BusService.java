package com.magdemy.travelark.service;

import com.magdemy.travelark.model.Bus;
import com.magdemy.travelark.model.Passenger;
import com.magdemy.travelark.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    public String updateLocation(String busName, String latitude, String longitude, String altitude, String date, String time){
        Bus bus = busRepository.findByName(busName);
        List<Double> location = new ArrayList<Double>();
        location.add(Double.valueOf(latitude));
        location.add(Double.valueOf(longitude));
        location.add(Double.valueOf(altitude));
        try {
            bus.setLocation(location);
            return "Updated";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String boardPassenger(String busName,Passenger passenger,String latitude,String longitude){
        System.out.println(busName);
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        Bus bus = busRepository.findByName(busName);
        if(bus.getHistory().get(bus.getHistory().size()-1).getPassengerIds().contains(passenger.getId())){
            bus.getHistory().get(bus.getHistory().size()-1).getPassengerIds().remove(passenger.getId());
            passenger.getHistory().get(passenger.getHistory().size()-1).add(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            busRepository.save(bus);
            return "Dropped";
        }
        else{
            bus.getHistory().get(bus.getHistory().size()-1).getPassengerIds().add(passenger.getId());
            passenger.getHistory().add(List.of(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),latitude,longitude,currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            busRepository.save(bus);
            return "Boarded";
        }
    }
}
