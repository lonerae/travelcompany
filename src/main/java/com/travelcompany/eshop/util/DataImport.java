package com.travelcompany.eshop.util;

import com.travelcompany.eshop.enums.AirportCode;
import com.travelcompany.eshop.enums.CustomerCategory;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import java.math.BigDecimal;
import java.util.Date;

public class DataImport {

    private final CustomerRepository customerRepo;
    private final ItineraryRepository itineraryRepo;
    private final TicketRepository ticketRepo;

    public DataImport(CustomerRepository customerRepo, ItineraryRepository itineraryRepo, TicketRepository ticketRepo) {
        this.customerRepo = customerRepo;
        this.itineraryRepo = itineraryRepo;
        this.ticketRepo = ticketRepo;
    }

    public void importCustomers() {
        
        {
            Customer customer = new Customer();
            customer.setName("Maria Iordanou");
            customer.setEmail("miordanou@mail.com");
            customer.setAddress("Athens");
            customer.setNationality("Greek");
            customer.setCategory(CustomerCategory.INDIVIDUAL);
            customerRepo.create(customer);
        }

        {
            Customer customer = new Customer();
            customer.setName("Dimitrios Dimitriou");
            customer.setEmail("ddimitriou@mail.com");
            customer.setAddress("Athens");
            customer.setNationality("Greek");
            customer.setCategory(CustomerCategory.INDIVIDUAL);
            customerRepo.create(customer);
        }

        {
            Customer customer = new Customer();
            customer.setName("Ioannis Ioannou");
            customer.setEmail("iioannou@mail.com");
            customer.setAddress("Athens");
            customer.setNationality("Greek");
            customer.setCategory(CustomerCategory.BUSINESS);
            customerRepo.create(customer);
        }

        {
            Customer customer = new Customer();
            customer.setName("Antonio Molinari");
            customer.setEmail("amolinari@mail.com");
            customer.setAddress("Milan");
            customer.setNationality("Italian");
            customer.setCategory(CustomerCategory.INDIVIDUAL);
            customerRepo.create(customer);
        }

        {
            Customer customer = new Customer();
            customer.setName("Frederico Rossi");
            customer.setEmail("frossi@mail.com");
            customer.setAddress("Milan");
            customer.setNationality("Italian");
            customer.setCategory(CustomerCategory.INDIVIDUAL);
            customerRepo.create(customer);
        }

        {
            Customer customer = new Customer();
            customer.setName("Mario Conti");
            customer.setEmail("mconti@mail.com");
            customer.setAddress("Rome");
            customer.setNationality("Italian");
            customer.setCategory(CustomerCategory.BUSINESS);
            customerRepo.create(customer);
        }

        {
            Customer customer = new Customer();
            customer.setName("Nathan Martin");
            customer.setEmail("nmartin@mail.com");
            customer.setAddress("Lyon");
            customer.setNationality("French");
            customer.setCategory(CustomerCategory.BUSINESS);
            customerRepo.create(customer);
        }

        {
            Customer customer = new Customer();
            customer.setName("Enzo Collin");
            customer.setEmail("ecollin@mail.com");
            customer.setAddress("Lyon");
            customer.setNationality("French");
            customer.setCategory(CustomerCategory.INDIVIDUAL);
            customerRepo.create(customer);
        }

        {
            Customer customer = new Customer();
            customer.setName("Frederic Michel");
            customer.setEmail("fmichel@mail.com");
            customer.setAddress("Athens");
            customer.setNationality("French");
            customer.setCategory(CustomerCategory.INDIVIDUAL);
            customerRepo.create(customer);
        }

    }

    public void importItineraries() {

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.PAR);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 13, 35));
            itinerary.setPrice(new BigDecimal("300"));
            itineraryRepo.create(itinerary);
        }

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.LON);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 13, 40));
            itinerary.setPrice(new BigDecimal("420"));
            itineraryRepo.create(itinerary);
        }

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.AMS);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 13, 45));
            itinerary.setPrice(new BigDecimal("280"));
            itineraryRepo.create(itinerary);
        }

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.PAR);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 14, 20));
            itinerary.setPrice(new BigDecimal("310"));
            itineraryRepo.create(itinerary);
        }

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.DUB);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 14, 35));
            itinerary.setPrice(new BigDecimal("880"));
            itineraryRepo.create(itinerary);
        }

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.FRA);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 14, 55));
            itinerary.setPrice(new BigDecimal("380"));
            itineraryRepo.create(itinerary);
        }

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.FRA);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 15, 35));
            itinerary.setPrice(new BigDecimal("350"));
            itineraryRepo.create(itinerary);
        }

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.MEX);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 16, 0));
            itinerary.setPrice(new BigDecimal("1020"));
            itineraryRepo.create(itinerary);
        }

        {
            Itinerary itinerary = new Itinerary();
            itinerary.setDeparture(AirportCode.ATH);
            itinerary.setDestination(AirportCode.DUB);
            itinerary.setDepartureDate(new Date(2022, 2, 22, 16, 35));
            itinerary.setPrice(new BigDecimal("770"));
            itineraryRepo.create(itinerary);
        }

    }

    public void importTickets() {
        
//        1 2 Cash
//        2 3 Cash
//        3 3 Credit Card
//        2 4 Credit Card
//        3 4 Cash
//        4 7 Credit Card
//        5 7 Credit Card
//        2 9 Cash
//        1 3 Cash
        
    }

}
