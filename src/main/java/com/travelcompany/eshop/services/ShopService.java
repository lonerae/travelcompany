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

    /**
     * Adds a new customer to Customer Repository if all requirements are met
     *
     * @param customer
     * @throws CustomerException when customer is null or customer email is
     * travelcompany.com
     */
    void addCustomer(Customer customer) throws CustomerException;

    /**
     * Adds a new itinerary to Itinerary Repository if all requirements are met
     *
     * @param itinerary
     * @throws ItineraryException when itinerary is null or airport codes are
     * non-existent
     */
    void addItinerary(Itinerary itinerary) throws ItineraryException;

    /**
     * Calculates the final price of a ticket and adds it to the Ticket
     * Repository if all requirements are met
     *
     * @param ticket as Ticket
     * @throws TicketException when ticket it null, customer id is non-existent
     * or itinerary id is non-existent
     */
    void buyTicket(Ticket ticket) throws TicketException;

    /**
     *
     * @return all customers as a List
     */
    List<Customer> searchCustomer();

    /**
     * Searches for a customer based on their unique id
     *
     * @param customerId
     * @return customer as Customer
     */
    Customer searchCustomer(int customerId);

    /**
     *
     * @return all itineraries as a List
     */
    List<Itinerary> searchItinerary();

    /**
     * Searches for an itinerary based on its unique id
     *
     * @param itineraryId
     * @return itinerary as Itinerary
     */
    Itinerary searchItinerary(int itineraryId);

    /**
     *
     * @return all tickets as a List
     */
    List<Ticket> searchTicket();

    /**
     * Searches for a ticket based on its unique id
     *
     * @param ticketId
     * @return ticket as Ticket
     */
    Ticket searchTicket(int ticketId);

    /**
     * Calculates final prices of all tickets in Ticket Repository (for price
     * calculation of imported data)
     */
    void calculatePrice();

    /**
     * Calculates an itinerary's final price and sets it to the corresponding
     * ticket
     *
     * @param ticket as Ticket
     */
    void calculatePrice(Ticket ticket);

    /**
     * Calculates the total number and cost of purchased tickets
     *
     * @return StatisticalDtoTotals
     */
    StatisticalDtoTotals calculateTotals();

    /**
     * Finds the number of purchased tickets from and to each airport
     *
     * @return StatisticalDtoAirports as List
     */
    List<StatisticalDtoAirports> calculateOfferedItinerariesPerAirport();

    /**
     * Finds the name(s) of the customer(s) with the most tickets
     *
     * @return StatisticalDtoMaxTicketCustomers as List
     */
    List<StatisticalDtoMaxTicketCustomers> findMaxTicketCustomers();

    /**
     * Finds the name(s) of the customer(s) with the largest cost of purchased
     * tickets
     *
     * @return StatisticalDtoMaxCostCustomers as List
     */
    List<StatisticalDtoMaxCostCustomers> calculateMaxCostCustomers();

    /**
     * Finds the names of all customers with no purchases
     *
     * @return StatisticalDtoNoTicketCustomers as List
     */
    List<StatisticalDtoNoTicketCustomers> calculateZeroTicketCustomers();
}
