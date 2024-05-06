package com.magdemy.travelark.service;

import com.magdemy.travelark.model.Bus;
import com.magdemy.travelark.model.BusHistoryEntry;
import com.magdemy.travelark.model.Passenger;
import com.magdemy.travelark.model.PassengerHistoryEntry;
import com.magdemy.travelark.repository.BusRepository;
import com.magdemy.travelark.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    public String updateLocation(String busName, String latitude, String longitude, String altitude, String date, String time){
        Bus bus = busRepository.findByName(busName);
        List<Double> location = new ArrayList<>();
        location.add(Double.valueOf(latitude));
        location.add(Double.valueOf(longitude));
        location.add(Double.valueOf(altitude));
        try {
            bus.setLocation(location);
            busRepository.save(bus);
            return "Updated";
        } catch (Exception e){
            return e.toString();
        }
    }

    public String boardPassenger(String busName, Passenger passenger, String date){
        // Get the current time in the Kolkata time zone
        LocalTime currentTimeInKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));

        // Format the time as a string (HH:mm:ss format)
        String currentTime = currentTimeInKolkata.toString(); // This will format the time as HH:mm:ss

        Bus bus = busRepository.findByName(busName);
        List<BusHistoryEntry> busHistoryEntries = bus.getHistory();

        String latitude = bus.getLocation().get(0).toString();
        String longitude = bus.getLocation().get(1).toString();

        boolean busHistroyFlag = false;

        for (BusHistoryEntry busHistoryEntry : busHistoryEntries) {
            if (busHistoryEntry.getDate().equals(date)) {
                if(passenger.getRole().equals("Driver")){
                    if(busHistoryEntry.getDriverId()==null){
                        busHistoryEntry.setDriverId(passenger.getId());
                        busHistoryEntry.setDriverName(passenger.getName());
                        busHistoryEntry.setPassengerIds(new ArrayList<String>());
                        bus.setHistory(busHistoryEntries);
                        busRepository.save(bus);
                        initialPassenger(passenger, latitude, longitude, date, currentTime);
                        return "Boarded";
                    }
                    else{
                        setDropTime(passenger, date, currentTime, latitude, longitude);
                    }
                }
                else {
                    if(!busHistoryEntry.getPassengerIds().contains(passenger.getId())){
                        busHistoryEntry.getPassengerIds().add(passenger.getId());
                        bus.setHistory(busHistoryEntries);
                        busRepository.save(bus);
                        initialPassenger(passenger, latitude, longitude, date, currentTime);
                        return "Boarded";
                    }
                    else {
                        setDropTime(passenger, date, currentTime, latitude, longitude);
                    }
                }
                busHistroyFlag = true;
                break;
            }
        }

        if(!busHistroyFlag){
            BusHistoryEntry newBusHistory = new BusHistoryEntry();
            newBusHistory.setDate(date);
            if(passenger.getRole().equals("Driver")){
                newBusHistory.setDriverId(passenger.getId());
                newBusHistory.setDriverName(passenger.getName());
            }
            else {
                newBusHistory.setPassengerIds(List.of(passenger.getId()));
            }
            bus.getHistory().add(newBusHistory);
            busRepository.save(bus);
            initialPassenger(passenger, latitude, longitude, date, currentTime);
            return "Boarded";
        }
        return "Dropped";
    }

    public void initialPassenger(Passenger passenger, String latitude, String longitude, String currentDate, String currentTime){
        PassengerHistoryEntry newPassengerHistory = new PassengerHistoryEntry();
        newPassengerHistory.setDate(currentDate);
        newPassengerHistory.setLongitude(longitude);
        newPassengerHistory.setLatitude(latitude);
        newPassengerHistory.setIntime(currentTime);
        passenger.getHistory().add(newPassengerHistory);
        passengerRepository.save(passenger);
    }

    public void setDropTime(Passenger passenger, String date, String currentTime, String latitude, String longitude){
        List<PassengerHistoryEntry> passengerHistoryEntries = passenger.getHistory();
        for(PassengerHistoryEntry passengerHistoryEntry: passengerHistoryEntries){
            if(passengerHistoryEntry.getDate().equals(date)){
                passengerHistoryEntry.setDroptime(currentTime);
                passengerHistoryEntry.setDropLatitude(latitude);
                passengerHistoryEntry.setDropLongitude(longitude);
                break;
            }
        }
        passenger.setHistory(passengerHistoryEntries);
        passengerRepository.save(passenger);
    }

    public String getBusLocation(String busName){
        Bus bus = busRepository.findByName(busName);
        return Double.toString(bus.getLocation().get(0))+","+Double.toString(bus.getLocation().get(1));
    }

    /*
    public String boardPassenger(String busName, Passenger passenger, String latitude, String longitude, String date){
        LocalTime currentTime = LocalTime.now();
        Bus bus = busRepository.findByName(busName);
        List<BusHistoryEntry> busHistoryEntries = bus.getHistory();
        List<PassengerHistoryEntry> passengerHistoryEntries = passenger.getHistory();
        boolean passengerFound = false;

        for (BusHistoryEntry busHistoryEntry : busHistoryEntries) {
            if (busHistoryEntry.getDate().equals(date)) {
                if(passenger.getRole().equals("Driver")){
                    if(busHistoryEntry.getDriverId()==null){
                        busHistoryEntry.setDriverId(passenger.getId());
                        busHistoryEntry.setDriverName(passenger.getName());
                        busRepository.save(bus);
                        return "Driver Boarded";
                    }
                }
                else {
                    if(!busHistoryEntry.getPassengerIds().contains(passenger.getId())){
                        busHistoryEntry.getPassengerIds().add(passenger.getId());
                        busRepository.save(bus);
                        PassengerHistoryEntry newPassengerHistoryEntry = new PassengerHistoryEntry();
                        newPassengerHistoryEntry.setDate(date);
                        newPassengerHistoryEntry.setIntime(String.valueOf(currentTime));
                        newPassengerHistoryEntry.setLatitude(latitude);
                        newPassengerHistoryEntry.setLongitude(longitude);
                        passenger.getHistory().add(newPassengerHistoryEntry);
                        passengerRepository.save(passenger);
                        return "Passenger Boarded";
                    }
                }
                for(PassengerHistoryEntry passengerHistoryEntry : passengerHistoryEntries){
                    if(passengerHistoryEntry.getDate().equals(date)){
                        passengerHistoryEntry.setDroptime(String.valueOf(currentTime));
                    }
                }
                passengerRepository.save(passenger);
                passengerFound = true;
                break;
            }
        }
        if (!passengerFound) {
            BusHistoryEntry newBusHistoryEntity = new BusHistoryEntry();
            newBusHistoryEntity.setDate(date);
            if(passenger.getRole().equals("Driver")) {
                newBusHistoryEntity.setDriverId(passenger.getId());
                newBusHistoryEntity.setDriverName(passenger.getName());
            }
            else {
                if(newBusHistoryEntity.getPassengerIds()==null) {
                    newBusHistoryEntity.setPassengerIds(new ArrayList<>());
                }
                newBusHistoryEntity.getPassengerIds().add(passenger.getId());
            }
            busHistoryEntries.add(newBusHistoryEntity);
            busRepository.save(bus);
            PassengerHistoryEntry newPassengerHistoryEntry = new PassengerHistoryEntry();
            newPassengerHistoryEntry.setDate(date);
            newPassengerHistoryEntry.setIntime(String.valueOf(currentTime));
            newPassengerHistoryEntry.setLatitude(latitude);
            newPassengerHistoryEntry.setLongitude(longitude);
            passenger.getHistory().add(newPassengerHistoryEntry);
            passengerRepository.save(passenger);
            return "Boarded";
        }
        return "Dropped";
    }
    */

    /*public String boardPassenger(String busName, Passenger passenger, String latitude, String longitude, String date) {
        LocalDate currentDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Bus bus = busRepository.findByName(busName);
        List<BusHistoryEntry> busHistoryEntries = bus.getHistory();
        boolean passengerFound = false;

        if (passenger.getRole().equals("Driver")) {
            for (BusHistoryEntry historyEntry : busHistoryEntries) {
                if (historyEntry.getDate().equals(date)) {
                    //if the role is driver and the history in bus data is found
                    passengerFound = true;
                    break;
                }
            }
            //if the role is driver and the history in bus data is not found
            if (!passengerFound) {
                BusHistoryEntry newHistoryEntry = new BusHistoryEntry();
                newHistoryEntry.setDate(date);
                newHistoryEntry.setDriverId(passenger.getId());
                newHistoryEntry.setDriverName(passenger.getName());
                newHistoryEntry.setPassengerIds(new ArrayList<>());
                busHistoryEntries.add(newHistoryEntry);
                busRepository.save(bus);
                return "New history entry created for driver.";
            }
        } else {
            for (BusHistoryEntry historyEntry : busHistoryEntries) {
                if (historyEntry.getDate().equals(date)) {
                    //if the user is passenger or driver and history found
                    passengerFound = true;
                    break;
                }
            }
            //if the user is passenger or driver and history not found
            if (!passengerFound) {
                BusHistoryEntry newHistoryEntry = new BusHistoryEntry();
                newHistoryEntry.setDate(date);
                newHistoryEntry.setPassengerIds(new ArrayList<>());
                newHistoryEntry.getPassengerIds().add(passenger.getId());
                newHistoryEntry.setPassengerHistoryEntries(new ArrayList<>());
                newHistoryEntry.getPassengerHistoryEntries().add(new PassengerHistoryEntry(date, latitude, longitude, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), ""));
                busHistoryEntries.add(newHistoryEntry);
                busRepository.save(bus);
                return "New history entry created for passenger.";
            }
        }

        return "Passenger history updated.";
    }

    private void updatePassengerHistory(BusHistoryEntry historyEntry, String passengerId, String latitude, String longitude) {
        List<PassengerHistoryEntry> passengerHistoryEntries = historyEntry.getPassengerHistoryEntries();
        for (PassengerHistoryEntry passengerHistoryEntry : passengerHistoryEntries) {
            if (passengerHistoryEntry.getDate().equals(LocalDate.now().toString())) {
                passengerHistoryEntry.setDroptime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                return;
            }
        }
        passengerHistoryEntries.add(new PassengerHistoryEntry(LocalDate.now().toString(), latitude, longitude, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), ""));
        historyEntry.getPassengerIds().add(passengerId);
    }*/
}
