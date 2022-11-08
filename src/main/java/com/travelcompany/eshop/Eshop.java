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

        //Will implement better presentation
        //For now, check JsonPrint.java
        System.out.println("---------------------");
        System.out.println("IMPORTED DATA PRINTING");
        System.out.println(shopService.searchCustomer());
        System.out.println(shopService.searchItinerary());
        System.out.println(shopService.searchTicket());
        System.out.println("---------------------");
        System.out.println("TICKET PURCHASING SCENARIO");
        Ticket ticket = new Ticket();
        ticket.setCustomerId(0);
        ticket.setItineraryId(1);
        ticket.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        shopService.buyTicket(ticket); //payment amount not specified, will be calculated by the application
        //basicPrice : 420, INDIVIDUAL : +20%, CREDIT_CARD: -10% -> expected finalPrice : 462
        System.out.println(ticket.getPaymentAmount());
        System.out.println("---------------------");
        System.out.println("NUMBER OF TICKETS AND TICKET COST PER CUSTOMER");
        List<Integer> ticketsPerCustomerList = shopService.ticketsPerCustomer();
        List<BigDecimal> costPerCustomerList = shopService.costPerCustomer();
        int customerNumber = shopService.searchCustomer().size();
        for (int i = 0; i < customerNumber; i++) {
            System.out.println(customerRepo.read(i).getName());
            System.out.println("    Number of Tickets : " + ticketsPerCustomerList.get(i));
            System.out.println("    Total Cost of Tickets : " + costPerCustomerList.get(i));
        }
        //Check validity for customer 0
        for (Ticket t : shopService.searchCustomer(0).getTicketList()) {
            System.out.println(t.getPaymentAmount());
        }
        
    }
}
