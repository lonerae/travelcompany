package com.travelcompany.eshop;

import com.google.gson.GsonBuilder;
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
import java.math.BigDecimal;
import java.util.List;

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

        System.out.println("---------------------");
        System.out.println("IMPORTED DATA PRINTING");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.searchCustomer()));
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.searchItinerary()));
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.searchTicket()));

        System.out.println("---------------------");
        System.out.println("TOTAL NUMBER OF TICKETS AND TICKET COST");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.calculateTotals()));

        System.out.println("---------------------");
        System.out.println("TOTAL ITINERARIES PER DEPARTURE AND DESTINATION");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.calculateItinerariesPerAirport()));
        
        System.out.println("---------------------");
        System.out.println("CUSTOMERS WITH MAX TICKETS AND MAX COST");

        
        System.out.println("---------------------");
        System.out.println("CUSTOMERS WITH ZERO TICKETS");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.calculateZeroTicketCustomers()));

        System.out.println("---------------------");
        System.out.println("TICKET PURCHASING SCENARIO");
        Ticket ticket = new Ticket();
        ticket.setCustomerId(0);
        ticket.setItineraryId(1);
        ticket.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        shopService.buyTicket(ticket); //payment amount not specified, will be calculated by the application
        //basicPrice : 420, INDIVIDUAL : +20%, CREDIT_CARD: -10% -> expected finalPrice : 462
        System.out.println(ticket.getPaymentAmount());
    }
}
