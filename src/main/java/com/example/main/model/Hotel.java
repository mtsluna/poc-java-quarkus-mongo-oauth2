package com.example.main.model;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@MongoEntity(collection = "Hotel", database = "demo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel extends PanacheMongoEntity {

    private String name;
    private String description;
    private int stars;
    private Set<Service> services;
    private Set<Room> rooms;

}
