package com.travelcompany.eshop.services;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import java.util.List;

public interface ShopService {
    
    boolean addCustomer(Customer customer);
    boolean addItinerary(Itinerary itinerary);
    boolean buyTicket(Ticket ticket);
    
    List<Customer> searchCustomer();
    List<Itinerary> searchItinerary();
    List<Ticket> searchTicket();
}
