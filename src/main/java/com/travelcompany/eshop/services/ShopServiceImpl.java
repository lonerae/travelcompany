package com.travelcompany.eshop.services;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import java.util.List;

public class ShopServiceImpl implements ShopService {

    private final CustomerRepository customerRepo;
    private final ItineraryRepository itineraryRepo;
    private final TicketRepository ticketRepo;

    public ShopServiceImpl(CustomerRepository customerRepo, ItineraryRepository itineraryRepo, TicketRepository ticketRepo) {
        this.customerRepo = customerRepo;
        this.itineraryRepo = itineraryRepo;
        this.ticketRepo = ticketRepo;
    }    
    
    @Override
    public boolean addCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }
        customerRepo.create(customer);
        return true;
    }

    @Override
    public boolean addItinerary(Itinerary itinerary) {
        if (itinerary == null) {
            return false;
        }
        itineraryRepo.create(itinerary);
        return true;
    }

    @Override
    public boolean buyTicket(Ticket ticket) {
        if (ticket == null) {
            return false;
        }
        ticketRepo.create(ticket);
        return true;
    }

    @Override
    public List<Customer> searchCustomer() {
        return customerRepo.read();
    }

    @Override
    public List<Itinerary> searchItinerary() {
        return itineraryRepo.read();
    }

    @Override
    public List<Ticket> searchTicket() {
        return ticketRepo.read();
    }
    
    
    
}
