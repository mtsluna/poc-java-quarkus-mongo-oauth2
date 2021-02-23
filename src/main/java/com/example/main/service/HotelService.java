package com.example.main.service;

import com.example.main.model.Hotel;
import com.example.main.repository.HotelRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class HotelService {

    @Inject
    HotelRepository hotelRepository;

    public Hotel save(Hotel hotel){

        hotelRepository.persist(hotel);
        return hotel;

    }

    public List<Hotel> findAll(){

        return hotelRepository.findAll().list();

    }

    public Hotel findOne(ObjectId objectId){

        return hotelRepository.findById(objectId);

    }

    public Hotel update(ObjectId objectId, Hotel hotel){

        hotel.id = objectId;
        hotelRepository.update(hotel);
        return hotel;

    }

    public void deleteOne(ObjectId objectId){

        hotelRepository.deleteById(objectId);

    }

    public void deleteAll(){

        hotelRepository.deleteAll();

    }

}
