package com.travelcompany.eshop.services;

import com.travelcompany.eshop.dto.StatisticalDtoItineraries;
import com.travelcompany.eshop.dto.StatisticalDtoTotals;
import com.travelcompany.eshop.dto.StatisticalDtoZeroTicketCustomers;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import java.util.List;

public interface ShopService {

    boolean addCustomer(Customer customer);

    boolean addItinerary(Itinerary itinerary);

    boolean buyTicket(Ticket ticket);

    List<Customer> searchCustomer();

    Customer searchCustomer(int customerId);

    List<Itinerary> searchItinerary();

    Itinerary searchItinerary(int itineraryId);

    List<Ticket> searchTicket();

    Ticket searchTicket(int ticketId);

    void calculatePrice();

    void calculatePrice(Ticket ticket);

    StatisticalDtoTotals calculateTotals();

    List<StatisticalDtoItineraries> calculateItinerariesPerAirport();
    
    List<StatisticalDtoZeroTicketCustomers> calculateZeroTicketCustomers();
}
