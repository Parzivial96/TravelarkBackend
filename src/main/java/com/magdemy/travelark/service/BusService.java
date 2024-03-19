package com.magdemy.travelark.service;

import com.magdemy.travelark.model.Bus;
import com.magdemy.travelark.model.Passenger;
import com.magdemy.travelark.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

    public String boardPassenger(String busName,Passenger passenger,String latitude,String longitude){
        System.out.println(busName);
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        Bus bus = busRepository.findByName(busName);
        if(bus.getPassengerIds().contains(passenger.getId())){
            bus.getPassengerIds().remove(passenger.getId());
            passenger.getHistory().get(passenger.getHistory().size()-1).add(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            busRepository.save(bus);
            return "Dropped";
        }
        else{
            bus.getPassengerIds().add(passenger.getId());
            passenger.getHistory().add(List.of(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),latitude,longitude,currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            busRepository.save(bus);
            return "Boarded";
        }
    }
}
