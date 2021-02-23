package com.example.main.controller;

import com.example.main.model.Hotel;
import com.example.main.service.HotelService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Consumes("application/json")
@Produces("application/json")
@Path("/hotels")
public class HotelController {

    @Inject
    HotelService hotelService;

    @POST
    @Path("/")
    public Response post(Hotel hotel){

        return Response.status(Response.Status.CREATED).entity(hotelService.save(hotel)).build();

    }

    @GET
    public Response getAll(){

        return Response.status(Response.Status.OK).entity(hotelService.findAll()).build();

    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") ObjectId id){

        return Response.status(Response.Status.OK).entity(hotelService.findOne(id)).build();

    }

    @PUT
    @Path("/{id}")
    public Response put(@PathParam("id") ObjectId id, Hotel hotel){

        return Response.status(Response.Status.CREATED).entity(hotelService.update(id, hotel)).build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") ObjectId id){

        hotelService.deleteOne(id);
        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @DELETE
    public Response deleteAll(){

        hotelService.deleteAll();
        return Response.status(Response.Status.NO_CONTENT).build();

    }

}
