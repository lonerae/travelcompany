package com.travelcompany.eshop.util;

import com.travelcompany.eshop.enums.AirportCode;
import com.travelcompany.eshop.enums.CustomerCategory;
import com.travelcompany.eshop.enums.PaymentMethod;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import java.math.BigDecimal;
import java.util.Date;

public class DataImport {

    private final CustomerRepository customerRepo;
    private final ItineraryRepository itineraryRepo;
    private final TicketRepository ticketRepo;

    private final static String[] CUSTOMERS = {
        "Maria Iordanou, miordanou@mail.com, Athens, Greek, INDIVIDUAL",
        "Dimitrios Dimitriou, ddimitriou@mail.com, Athens, Greek, INDIVIDUAL",
        "Ioannis Ioannou, iioannou@mail.com, Athens, Greek, BUSINESS",
        "Antonio Molinari, amolinari@mail.com, Milan, Italian, INDIVIDUAL",
        "Frederico Rossi, frossi@mail.com, Milan, Italian, INDIVIDUAL",
        "Mario Conti, mconti@mail.com, Rome, Italian, BUSINESS",
        "Nathan Martin, nmartin@mail.com, Lyon, French, BUSINESS",
        "Enzo Collin, ecollin@mail.com, Lyon, French, INDIVIDUAL",
        "Frederic Michel, fmichel@mail.com, Athens, French, INDIVIDUAL"
    };

    private final static String[] ITINERARIES = {
        "ATH, PAR, 2022-02-22 13:35, 300",
        "ATH, LON, 2022-02-22 13:40, 420",
        "ATH, AMS, 2022-02-22 13:45, 280",
        "ATH, PAR, 2022-02-22 14:20, 310",
        "ATH, DUB, 2022-02-22 14:35, 880",
        "ATH, FRA, 2022-02-22 14:55, 380",
        "ATH, FRA, 2022-02-22 15:35, 350",
        "ATH, MEX, 2022-02-22 16:00, 1020",
        "ATH, DUB, 2022-02-22 16:35, 770"
    };

    private final static String[] TICKETS = {
        "0, 1, CASH",
        "1, 2, CASH",
        "2, 2, CREDIT_CARD",
        "1, 3, CREDIT_CARD",
        "2, 3, CASH",
        "3, 6, CREDIT_CARD",
        "4, 6, CREDIT_CARD",
        "1, 8, CASH",
        "0, 2, CASH"
    };

    public DataImport(CustomerRepository customerRepo, ItineraryRepository itineraryRepo, TicketRepository ticketRepo) {
        this.customerRepo = customerRepo;
        this.itineraryRepo = itineraryRepo;
        this.ticketRepo = ticketRepo;
    }

    public void importCustomers() {
        for (String customerString : CUSTOMERS) {
            String[] details = customerString.split(",");
            Customer customer = new Customer();
            customer.setName(details[0].trim());
            customer.setEmail(details[1].trim());
            customer.setAddress(details[2].trim());
            customer.setNationality(details[3].trim());
            customer.setCategory(CustomerCategory.valueOf(details[4].trim()));
            customerRepo.create(customer);
        }
    }

    public void importItineraries() {
        for (String itineraryString : ITINERARIES) {
            String[] details = itineraryString.split(",");
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.valueOf(details[0].trim()));
            itinerary.setDestination(AirportCode.valueOf(details[1].trim()));
            String[] dateAndTimeParts = details[2].trim().split(" ");
            String[] dateParts = dateAndTimeParts[0].split("-");
            String[] timeParts = dateAndTimeParts[1].split(":");
            itinerary.setDepartureDate(new Date(
                    Integer.parseInt(dateParts[0]) - 1900,
                    Integer.parseInt(dateParts[1]) - 1,
                    Integer.parseInt(dateParts[2]),
                    Integer.parseInt(timeParts[0]),
                    Integer.parseInt(timeParts[1])
            ));
            itinerary.setPrice(BigDecimal.valueOf(Double.parseDouble(details[3].trim())));
            itineraryRepo.create(itinerary);
        }

    }

    public void importTickets() {
        for (String ticketString : TICKETS) {
            String[] details = ticketString.split(",");
            Ticket ticket = new Ticket();
            ticket.setCustomerId(Integer.parseInt(details[0].trim()));
            ticket.setItineraryId(Integer.parseInt(details[1].trim()));
            ticket.setPaymentMethod(PaymentMethod.valueOf(details[2].trim()));
            ticketRepo.create(ticket);
            customerRepo.read(ticket.getCustomerId()).getTicketList().add(ticket);
        }
    }

}
