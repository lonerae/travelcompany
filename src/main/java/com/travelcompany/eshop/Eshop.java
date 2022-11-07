package com.travelcompany.eshop;

import com.travelcompany.eshop.enums.PaymentMethod;
import com.travelcompany.eshop.model.Ticket;
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
        dataImport.importTickets();

        shopService.calculatePrice();

        //Will implement better presentation
        //For now, check JsonPrint.java
        System.out.println(shopService.searchCustomer());
        System.out.println(shopService.searchItinerary());
        System.out.println(shopService.searchTicket());

        //Ticket purchasing scenario
        Ticket ticket = new Ticket();
        ticket.setCustomerId(1);
        ticket.setItineraryId(2);
        ticket.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        shopService.buyTicket(ticket); //payment amount not specified, will be calculated by the application
        //basicPrice : 420, INDIVIDUAL : +20%, CREDIT_CARD: -10% -> expected finalPrice : 462
        System.out.println(ticket.getPaymentAmount());
    }
}
