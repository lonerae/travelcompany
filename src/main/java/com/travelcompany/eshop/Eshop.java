package com.travelcompany.eshop;

import com.google.gson.GsonBuilder;
import com.travelcompany.eshop.enums.PaymentMethod;
import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import com.travelcompany.eshop.repository.impl.CustomerRepositoryImpl;
import com.travelcompany.eshop.repository.impl.ItineraryRepositoryImpl;
import com.travelcompany.eshop.repository.impl.TicketRepositoryImpl;
import com.travelcompany.eshop.services.IoService;
import com.travelcompany.eshop.services.impl.IoServiceImpl;
import com.travelcompany.eshop.services.ShopService;
import com.travelcompany.eshop.services.impl.ShopServiceImpl;

public class Eshop {

    public static void main(String[] args) {

        CustomerRepository customerRepo = new CustomerRepositoryImpl();
        ItineraryRepository itineraryRepo = new ItineraryRepositoryImpl();
        TicketRepository ticketRepo = new TicketRepositoryImpl();

        ShopService shopService = new ShopServiceImpl(customerRepo, itineraryRepo, ticketRepo);
        IoService ioService = new IoServiceImpl(customerRepo, itineraryRepo, ticketRepo);

        try {
            ioService.readCustomerFromCsv("data/customers.csv");
            ioService.readItineraryFromCsv("data/itineraries.csv");
            ioService.readTicketFromCsv("data/tickets.csv");
        } catch (CustomerException | ItineraryException | TicketException ex) {
            System.out.println(ex.getMessage());
        }

        shopService.calculatePrice();

        System.out.println("---------------------");
        System.out.println("IMPORTED DATA PRINTING");

        System.out.println("---------------------");
        System.out.println("CUSTOMERS");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.searchCustomer()));
        System.out.println("---------------------");
        System.out.println("ITINERARIES");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.searchItinerary()));
        System.out.println("---------------------");
        System.out.println("TICKETS");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.searchTicket()));

        System.out.println("---------------------");
        System.out.println("TOTAL NUMBER OF TICKETS AND TICKET COST");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.calculateTotals()));

        System.out.println("---------------------");
        System.out.println("TOTAL OFFERED ITINERARIES TO CUSTOMERS, PER DEPARTURE AND DESTINATION");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.calculateOfferedItinerariesPerAirport()));

        System.out.println("---------------------");
        System.out.println("CUSTOMERS WITH MAX TICKETS");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.findMaxTicketCustomers()));
        System.out.println("---------------------");
        System.out.println("CUSTOMERS WITH MAX COST");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.calculateMaxCostCustomers()));

        System.out.println("---------------------");
        System.out.println("CUSTOMERS WITH ZERO TICKETS");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(shopService.calculateZeroTicketCustomers()));

        try {
            ioService.saveCustomersToCsv("data/customers.csv");
            ioService.saveItineraryToCsv("data/itineraries.csv");
            ioService.saveTicketToCsv("data/tickets.csv");
        } catch (CustomerException | ItineraryException | TicketException ex) {
            System.out.println(ex.getMessage());
        }

//      After CSV file is saved, to keep initial data intact (testing purposes)
        System.out.println("---------------------");
        System.out.println("TICKET PURCHASING SCENARIO");
        Ticket ticket = new Ticket();
        ticket.setCustomerId(0);
        ticket.setItineraryId(1);
        ticket.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        try {
            shopService.buyTicket(ticket); //payment amount not specified, will be calculated by the application
            System.out.println("PURCHASE SUCCESSFUL");
        } catch (TicketException ex) {
            System.out.println(ex.getMessage());
        }
        //basicPrice : 420, INDIVIDUAL : +20%, CREDIT_CARD: -10% -> expected finalPrice : 462
        System.out.println("Customer: " + shopService.searchCustomer(ticket.getCustomerId()).getName()
                + "\nDeparts from: " + shopService.searchItinerary(ticket.getItineraryId()).getDeparture()
                + "\nArrives to: " + shopService.searchItinerary(ticket.getItineraryId()).getDestination()
                + "\nOn: " + shopService.searchItinerary(ticket.getItineraryId()).getDepartureDate()
                + "\nFinal Price: " + ticket.getPaymentAmount() + "€"
        );
    }
}
