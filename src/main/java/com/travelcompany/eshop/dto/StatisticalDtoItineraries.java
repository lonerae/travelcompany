package com.travelcompany.eshop.dto;

import com.travelcompany.eshop.enums.AirportCode;

public class StatisticalDtoItineraries {

    private AirportCode airportCode;
    private int departureCount;
    private int destinationCount;

    public AirportCode getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(AirportCode airportCode) {
        this.airportCode = airportCode;
    }

    public int getDepartureCount() {
        return departureCount;
    }

    public void setDepartureCount(int departureCount) {
        this.departureCount = departureCount;
    }

    public int getDestinationCount() {
        return destinationCount;
    }

    public void setDestinationCount(int destinationCount) {
        this.destinationCount = destinationCount;
    }

}
