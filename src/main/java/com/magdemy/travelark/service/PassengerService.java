package com.magdemy.travelark.service;

import com.magdemy.travelark.model.Passenger;
import com.magdemy.travelark.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger addPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public List<Optional<Passenger>> getPassengerById(List<String> passengerIdList){
        List<Optional<Passenger>> passengerList = new ArrayList<>();
        for(String passengerId:passengerIdList){
            passengerList.add(passengerRepository.findById(passengerId));
        }
        return passengerList;
    }
}
