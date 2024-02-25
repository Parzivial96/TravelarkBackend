package com.magdemy.travelark.repository;

import com.magdemy.travelark.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus, String> {
}
