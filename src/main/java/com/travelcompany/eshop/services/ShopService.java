package com.travelcompany.eshop.services;

import com.travelcompany.eshop.dto.StatisticalDtoAirports;
import com.travelcompany.eshop.dto.StatisticalDtoMaxCostCustomers;
import com.travelcompany.eshop.dto.StatisticalDtoMaxTicketCustomers;
import com.travelcompany.eshop.dto.StatisticalDtoTotals;
import com.travelcompany.eshop.dto.StatisticalDtoNoTicketCustomers;
import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import java.util.List;

public interface ShopService {

    void addCustomer(Customer customer) throws CustomerException;

    void addItinerary(Itinerary itinerary) throws ItineraryException;

    void buyTicket(Ticket ticket) throws TicketException;

    List<Customer> searchCustomer();

    Customer searchCustomer(int customerId);

    List<Itinerary> searchItinerary();

    Itinerary searchItinerary(int itineraryId);

    List<Ticket> searchTicket();

    Ticket searchTicket(int ticketId);

    void calculatePrice();

    void calculatePrice(Ticket ticket);

    StatisticalDtoTotals calculateTotals();

    List<StatisticalDtoAirports> calculateOfferedItinerariesPerAirport();
    
    List<StatisticalDtoMaxTicketCustomers> calculateMaxTicketCustomers();
    
    List<StatisticalDtoMaxCostCustomers> calculateMaxCostCustomers();
    
    List<StatisticalDtoNoTicketCustomers> calculateZeroTicketCustomers();
}
