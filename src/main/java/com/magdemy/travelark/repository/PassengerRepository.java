package com.magdemy.travelark.repository;

import com.magdemy.travelark.model.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassengerRepository extends MongoRepository<Passenger,String> {
}
