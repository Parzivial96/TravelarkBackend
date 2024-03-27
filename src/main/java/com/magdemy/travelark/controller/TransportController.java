package com.magdemy.travelark.controller;

import com.magdemy.travelark.model.Bus;
import com.magdemy.travelark.model.Passenger;
import com.magdemy.travelark.service.BusService;
import com.magdemy.travelark.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TransportController {

    @Autowired
    private BusService busService;

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/")
    public String apiOverview(){
        return """
                Available End Points:
                \\getAllBus
                \\getAllPassenger
                \\getPassengerById
                \\addBus
                \\addPassenger
                """;
    }

    @GetMapping("/getAllBus")
    public List<Bus> getAllBus() {
        return busService.getAllBuses();
    }

    @PostMapping("/addBus")
    public Bus addBus(@RequestBody Bus bus) {
        return busService.addBus(bus);
    }

    @GetMapping("/getAllPassenger")
    public List<Passenger> getAllPassenger() {
        return passengerService.getAllPassengers();
    }

    @PostMapping("/getPassengerById")
    public List<Optional<Passenger>> getPassengerById(@RequestBody List<String> passengerIdList){
        return passengerService.getPassengerById(passengerIdList);
    }
    @PostMapping("/addPassenger")
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }

    /*
    @PutMapping("/boardPassenger")
    public void boardPassenger(@RequestBody List<String> data){
        Passenger passenger = passengerService.getPassengerByRFID(data.get(1));
        busService.boardPassenger(data.get(0),passenger);
    }
    */

    @GetMapping("/boardPassenger")
    public String boardPassenger(@RequestParam String busName, @RequestParam String rfid, @RequestParam String latitude, @RequestParam String longitude, @RequestParam String date) {
        Passenger passenger = passengerService.getPassengerByRFID(rfid);
        return busService.boardPassenger(busName, passenger, latitude, longitude, date);
    }

    @PostMapping("/login")
    public Passenger login(@RequestBody Map<String, String> request){
        String phone = request.get("phone");
        String password = request.get("password");
        System.out.println(phone+" "+password);
        return passengerService.login(phone,password);
    }

    @PostMapping("/updateLocation")
    public String updateLocation(@RequestBody Map<String, String> request){
        String busName = request.get("busname");
        String latitude = request.get("latitude");
        String longitude = request.get("longitude");
        String altitude = request.get("altitude");
        String date = request.get("date");
        String time = request.get("time");
        return busService.updateLocation(busName, latitude, longitude, altitude, date, time);
    }
}

