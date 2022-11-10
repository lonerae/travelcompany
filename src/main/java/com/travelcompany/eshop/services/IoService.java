package com.travelcompany.eshop.services;

import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;

public interface IoService {
 
    void saveCustomersToCsv(String fileName) throws CustomerException;
    
    int readCustomerFromCsv(String fileName) throws CustomerException;
    
    void saveItineraryToCsv(String fileName) throws ItineraryException;
    
    int readItineraryFromCsv(String fileName) throws ItineraryException;
    
    void saveTicketToCsv(String fileName) throws TicketException;
    
    int readTicketFromCsv(String fileName) throws TicketException;

}
