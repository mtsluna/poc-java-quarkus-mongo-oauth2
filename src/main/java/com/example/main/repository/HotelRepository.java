package com.example.main.repository;

import com.example.main.model.Hotel;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HotelRepository implements PanacheMongoRepository<Hotel> {


}
