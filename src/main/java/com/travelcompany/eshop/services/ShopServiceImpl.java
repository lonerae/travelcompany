package com.travelcompany.eshop.services;

import com.travelcompany.eshop.dto.StatisticalDtoItineraries;
import com.travelcompany.eshop.dto.StatisticalDtoTotals;
import com.travelcompany.eshop.dto.StatisticalDtoZeroTicketCustomers;
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
import java.util.ArrayList;
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

    /**
     * Calculates the final price of the ticket and adds the newly purchased
     * ticket in the Ticket Repository.
     *
     * @param ticket as Ticket
     * @return true if purchase was successful
     */
    @Override
    public boolean buyTicket(Ticket ticket) {
        if (ticket == null) {
            return false;
        }
        calculatePrice(ticket);
        ticketRepo.create(ticket);
        customerRepo.read(ticket.getCustomerId()).getTicketList().add(ticket);
        return true;
    }
    
    @Override
    public List<Customer> searchCustomer() {
        return customerRepo.read();
    }
    
    @Override
    public Customer searchCustomer(int customerId) {
        return customerRepo.read(customerId);
    }
    
    @Override
    public List<Itinerary> searchItinerary() {
        return itineraryRepo.read();
    }
    
    @Override
    public Itinerary searchItinerary(int itineraryId) {
        return itineraryRepo.read(itineraryId);
    }
    
    @Override
    public List<Ticket> searchTicket() {
        return ticketRepo.read();
    }
    
    @Override
    public Ticket searchTicket(int ticketId) {
        return ticketRepo.read(ticketId);
    }

    /**
     * For price calculation of imported tickets.
     */
    @Override
    public void calculatePrice() {
        for (Ticket ticket : ticketRepo.read()) {
            calculatePrice(ticket);
        }
    }

    /**
     * Calculates an Itinerary's final price.
     *
     * CREDIT_CARD: -10% to basic price. BUSINESS: -10% to basic price.
     * INDIVIDUAL: +20% to basic price. (the price changes are cumulative)
     *
     * @param ticket as Ticket
     */
    @Override
    public void calculatePrice(Ticket ticket) {
        Customer customer = customerRepo.read(ticket.getCustomerId());
        Itinerary itinerary = itineraryRepo.read(ticket.getItineraryId());
        PaymentMethod paymentMethod = ticket.getPaymentMethod();
        
        BigDecimal basicPrice = itinerary.getPrice();
        
        double percent = 0;
        if (paymentMethod.equals(PaymentMethod.CREDIT_CARD)) {
            percent -= 10;
        }
        if (customer.getCategory().equals(CustomerCategory.BUSINESS)) {
            percent -= 10;
        } else {
            percent += 20;
        }
        
        BigDecimal priceDifference = basicPrice.multiply(BigDecimal.valueOf(percent / 100));
        BigDecimal finalPrice = basicPrice.add(priceDifference);
        
        ticket.setPaymentAmount(finalPrice);
    }
    
    @Override
    public StatisticalDtoTotals calculateTotals() {
        StatisticalDtoTotals total = new StatisticalDtoTotals();
        total.setTotalNumberOfTickets(ticketRepo.read().size());
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Ticket ticket : ticketRepo.read()) {
            totalCost = totalCost.add(ticket.getPaymentAmount());
        }
        total.setTotalCostOfTickets(totalCost);
        return total;
    }
    
    @Override
    public List<StatisticalDtoItineraries> calculateItinerariesPerAirport() {
        List<StatisticalDtoItineraries> airportList = new ArrayList<>();
        for (AirportCode airportCode : AirportCode.values()) {
            StatisticalDtoItineraries dto = new StatisticalDtoItineraries();
            dto.setAirportCode(airportCode);
            int departureCount = 0;
            int destinationCount = 0;
            for (Itinerary itinerary : itineraryRepo.read()) {
                if (itinerary.getDeparture().equals(airportCode)) {
                    departureCount++;
                }
                if (itinerary.getDestination().equals(airportCode)) {
                    destinationCount++;
                }
            }
            dto.setDepartureCount(departureCount);
            dto.setDestinationCount(destinationCount);
            airportList.add(dto);
        }
        return airportList;
    }

    @Override
    public List<StatisticalDtoZeroTicketCustomers> calculateZeroTicketCustomers() {
        List<StatisticalDtoZeroTicketCustomers> dtoList = new ArrayList<>();
        for (Customer customer : customerRepo.read()) {
            if (customer.getTicketList().isEmpty()) {
                StatisticalDtoZeroTicketCustomers dto = new StatisticalDtoZeroTicketCustomers();
                dto.setName(customer.getName());
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    
    
    
}
