package com.travelcompany.eshop.services;

import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;

public interface IoService {

    /**
     * Saves all customer information to a CSV file
     *
     * @param fileName of the destination file
     * @throws CustomerException when customer CSV file in not accessible
     */
    void saveCustomersToCsv(String fileName) throws CustomerException;

    /**
     * Imports all customers described in a CSV file to the Customer Repository
     *
     * @param fileName of the source file
     * @return number of customers added
     * @throws CustomerException when details cannot be parsed successfully
     */
    int readCustomerFromCsv(String fileName) throws CustomerException;

    /**
     * Saves all itinerary information to a CSV file
     *
     * @param fileName of the destination file
     * @throws ItineraryException when itinerary CSV file in not accessible
     */
    void saveItineraryToCsv(String fileName) throws ItineraryException;

    /**
     * Imports all itineraries described in a CSV file to the Itinerary
     * Repository
     *
     * @param fileName of the source file
     * @return number of itineraries added
     * @throws ItineraryException when details cannot be parsed successfully
     */
    int readItineraryFromCsv(String fileName) throws ItineraryException;

    /**
     * Saves all ticket information to a CSV file
     *
     * @param fileName of the destination file
     * @throws TicketException when ticket CSV file in not accessible
     */
    void saveTicketToCsv(String fileName) throws TicketException;

    /**
     * Imports all tickets described in a CSV file to the Ticket Repository
     *
     * @param fileName of the source file
     * @return number of tickets added
     * @throws TicketException when details cannot be parsed successfully
     */
    int readTicketFromCsv(String fileName) throws TicketException;

}
