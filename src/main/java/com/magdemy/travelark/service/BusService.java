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
}
