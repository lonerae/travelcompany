package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.AirportCode;
import java.math.BigDecimal;
import java.util.Date;

public class Itinerary extends PersistentClass {

    private AirportCode departure;
    private AirportCode destination;
    private Date departureDate;
    private String airline;
    private BigDecimal price;

    public AirportCode getDeparture() {
        return departure;
    }

    public void setDeparture(AirportCode departure) {
        this.departure = departure;
    }

    public AirportCode getDestination() {
        return destination;
    }

    public void setDestination(AirportCode destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
