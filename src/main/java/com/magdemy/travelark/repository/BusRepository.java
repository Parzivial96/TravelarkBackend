package com.magdemy.travelark.repository;

import com.magdemy.travelark.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusRepository extends MongoRepository<Bus, String> {

    Bus findByName(String name);
}
