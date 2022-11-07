package com.travelcompany.eshop.util;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import com.travelcompany.eshop.repository.impl.CustomerRepositoryImpl;
import com.travelcompany.eshop.repository.impl.ItineraryRepositoryImpl;
import com.travelcompany.eshop.repository.impl.TicketRepositoryImpl;
import com.travelcompany.eshop.services.ShopService;
import com.travelcompany.eshop.services.ShopServiceImpl;
import org.json.*;

/**
 * Test for better Data Presentation, using JSON
 */
public class JsonPrint {

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

        System.out.println("\nCUSTOMERS\n");
        for (Customer customer : shopService.searchCustomer()) {
            String json = "{"
                    + "Id : " + customer.getId() + ","
                    + "Name : " + customer.getName() + ","
                    + "Email : " + customer.getEmail() + ","
                    + "Address : " + customer.getAddress() + ","
                    + "Nationality : " + customer.getNationality() + ","
                    + "Category : " + customer.getCategory()
                    + "}";
            JSONObject jsonObj = new JSONObject(json);
            System.out.println(jsonObj.toString(4));
        }

        System.out.println("\nITINERARIES\n");
        for (Itinerary itinerary : shopService.searchItinerary()) {
            String json = "{"
                    + "Id : " + itinerary.getId() + ","
                    + "Departure Station : " + itinerary.getDeparture() + ","
                    + "Destination Station : " + itinerary.getDestination() + ","
                    + //"Departure Date : " + itinerary.getDepartureDate() + "," + 
                    //Problem with JSON parsing of Date objects, would change them either way
                    "Airline : " + Itinerary.AIRLINE + ","
                    + "Basic Price : " + itinerary.getPrice()
                    + "}";
            JSONObject jsonObj = new JSONObject(json);
            System.out.println(jsonObj.toString(4));
        }

        System.out.println("\nTICKETS\n");
        for (Ticket ticket : shopService.searchTicket()) {
            String json = "{"
                    + "Id : " + ticket.getId() + ","
                    + "Customer Id : " + ticket.getCustomerId() + ","
                    + "Itinerary Id : " + ticket.getItineraryId() + ","
                    + "Payment Method : " + ticket.getPaymentMethod() + ","
                    + "Final Price : " + ticket.getPaymentAmount()
                    + "}";
            JSONObject jsonObj = new JSONObject(json);
            System.out.println(jsonObj.toString(4));
        }

    }

}
