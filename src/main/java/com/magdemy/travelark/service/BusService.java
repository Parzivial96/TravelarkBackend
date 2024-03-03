package com.magdemy.travelark.service;

import com.magdemy.travelark.model.Bus;
import com.magdemy.travelark.model.Passenger;
import com.magdemy.travelark.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String boardPassenger(String busName,Passenger passenger){
        System.out.println(busName);
        Bus bus = busRepository.findByName(busName);
        if(bus.getPassengerIds().contains(passenger.getId())){
            bus.getPassengerIds().remove(passenger.getId());
            busRepository.save(bus);
            return "Dropped";
        }
        else{
            bus.getPassengerIds().add(passenger.getId());
            busRepository.save(bus);
            return "Boarded";
        }
    }
}
