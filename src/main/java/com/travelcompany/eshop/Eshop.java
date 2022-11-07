package com.travelcompany.eshop;

import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import com.travelcompany.eshop.repository.impl.CustomerRepositoryImpl;
import com.travelcompany.eshop.repository.impl.ItineraryRepositoryImpl;
import com.travelcompany.eshop.repository.impl.TicketRepositoryImpl;
import com.travelcompany.eshop.services.ShopService;
import com.travelcompany.eshop.services.ShopServiceImpl;
import com.travelcompany.eshop.util.DataImport;

public class Eshop {

    public static void main(String[] args) {
        
        CustomerRepository customerRepo = new CustomerRepositoryImpl();
        ItineraryRepository itineraryRepo = new ItineraryRepositoryImpl();
        TicketRepository ticketRepo = new TicketRepositoryImpl();
        
        ShopService shopService = new ShopServiceImpl(customerRepo, itineraryRepo, ticketRepo);
        DataImport dataImport = new DataImport(customerRepo, itineraryRepo, ticketRepo);
        
        dataImport.importCustomers();
        dataImport.importItineraries();
        
        System.out.println(shopService.searchCustomer());
        System.out.println(shopService.searchItinerary());
    }
}
