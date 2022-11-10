package com.travelcompany.eshop.services;

import com.travelcompany.eshop.dto.StatisticalDtoAirports;
import com.travelcompany.eshop.dto.StatisticalDtoMaxCostCustomers;
import com.travelcompany.eshop.dto.StatisticalDtoMaxTicketCustomers;
import com.travelcompany.eshop.dto.StatisticalDtoTotals;
import com.travelcompany.eshop.dto.StatisticalDtoNoTicketCustomers;
import com.travelcompany.eshop.enums.AirportCode;
import com.travelcompany.eshop.enums.CustomerCategory;
import com.travelcompany.eshop.enums.PaymentMethod;
import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.CustomerExceptionCodes;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.ItineraryExceptionCodes;
import com.travelcompany.eshop.exceptions.TicketException;
import com.travelcompany.eshop.exceptions.TicketExceptionCodes;
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
    public void addCustomer(Customer customer) throws CustomerException {
        if (customer == null) {
            throw new CustomerException(CustomerExceptionCodes.NULL);
        }
        if (customer.getEmail().contains("@travelcompany.com")) {
            throw new CustomerException(CustomerExceptionCodes.CUSTOMER_INVALID_EMAIL);
        }
        customerRepo.create(customer);
    }

    @Override
    public void addItinerary(Itinerary itinerary) throws ItineraryException {
        if (itinerary == null) {
            throw new ItineraryException(ItineraryExceptionCodes.NULL);
        }
        boolean departureAirportFlag = false;
        boolean destinationAirportFlag = false;
        for (AirportCode airportCode : AirportCode.values()) {
            if (itinerary.getDeparture().equals(airportCode)) {
                departureAirportFlag = true;
            }
            if (itinerary.getDestination().equals(airportCode)) {
                destinationAirportFlag = true;
            }
        }
        if (!departureAirportFlag) {
            throw new ItineraryException(ItineraryExceptionCodes.INVALID_DEPARTURE_CODE);
        }
        if (!destinationAirportFlag) {
            throw new ItineraryException(ItineraryExceptionCodes.INVALID_DESTINATION_CODE);
        }
        itineraryRepo.create(itinerary);
    }

    @Override
    public void buyTicket(Ticket ticket) throws TicketException {
        if (ticket == null) {
            throw new TicketException(TicketExceptionCodes.NULL);
        }
        if (ticket.getCustomerId() < 0 || ticket.getCustomerId() >= ticketRepo.read().size()) {
            throw new TicketException(TicketExceptionCodes.TICKET_CUSTOMER_IS_INVALID);
        }
        if (ticket.getItineraryId() < 0 || ticket.getItineraryId() >= ticketRepo.read().size()) {
            throw new TicketException(TicketExceptionCodes.TICKET_ITINERARY_IS_INVALID);
        }
        calculatePrice(ticket);
        ticketRepo.create(ticket);
        customerRepo.read(ticket.getCustomerId()).getTicketList().add(ticket);
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

    @Override
    public void calculatePrice() {
        for (Ticket ticket : ticketRepo.read()) {
            calculatePrice(ticket);
        }
    }

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
    public List<StatisticalDtoAirports> calculateOfferedItinerariesPerAirport() {
        List<StatisticalDtoAirports> airportList = new ArrayList<>();
        for (AirportCode airportCode : AirportCode.values()) {
            StatisticalDtoAirports dto = new StatisticalDtoAirports();
            dto.setAirportCode(airportCode);
            int departureCount = 0;
            int destinationCount = 0;
            for (Ticket ticket : ticketRepo.read()) {
                if (itineraryRepo.read(ticket.getItineraryId()).getDeparture().equals(airportCode)) {
                    departureCount++;
                }
                if (itineraryRepo.read(ticket.getItineraryId()).getDestination().equals(airportCode)) {
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
    public List<StatisticalDtoMaxTicketCustomers> findMaxTicketCustomers() {
        List<StatisticalDtoMaxTicketCustomers> dtoList = new ArrayList<>();
        int maxTickets = -1;
        for (Customer customer : customerRepo.read()) {
            StatisticalDtoMaxTicketCustomers dto = new StatisticalDtoMaxTicketCustomers();
            if (customer.getTicketList().size() >= maxTickets) {
                if (customer.getTicketList().size() > maxTickets) {
                    maxTickets = customer.getTicketList().size();
                    dtoList.clear();
                }
                dto.setName(customer.getName());
                dto.setMaxTickets(maxTickets);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    @Override
    public List<StatisticalDtoMaxCostCustomers> calculateMaxCostCustomers() {
        List<StatisticalDtoMaxCostCustomers> dtoList = new ArrayList<>();
        BigDecimal maxCost = BigDecimal.valueOf(-1);
        for (Customer customer : customerRepo.read()) {
            StatisticalDtoMaxCostCustomers dto = new StatisticalDtoMaxCostCustomers();
            BigDecimal currentCost = BigDecimal.ZERO;
            for (Ticket ticket : customer.getTicketList()) {
                currentCost = currentCost.add(ticket.getPaymentAmount());
            }
            if (currentCost.compareTo(maxCost) >= 0) {
                if (currentCost.compareTo(maxCost) > 0) {
                    maxCost = currentCost;
                    dtoList.clear();
                }
                dto.setName(customer.getName());
                dto.setMaxCost(maxCost);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    @Override
    public List<StatisticalDtoNoTicketCustomers> calculateZeroTicketCustomers() {
        List<StatisticalDtoNoTicketCustomers> dtoList = new ArrayList<>();
        for (Customer customer : customerRepo.read()) {
            if (customer.getTicketList().isEmpty()) {
                StatisticalDtoNoTicketCustomers dto = new StatisticalDtoNoTicketCustomers();
                dto.setName(customer.getName());
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

}
