package com.travelcompany.eshop.services;

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IoServiceImpl implements IoService {

    private final CustomerRepository customerRepo;
    private final ItineraryRepository itineraryRepo;
    private final TicketRepository ticketRepo;

    public IoServiceImpl(CustomerRepository customerRepo, ItineraryRepository itineraryRepo, TicketRepository ticketRepo) {
        this.customerRepo = customerRepo;
        this.itineraryRepo = itineraryRepo;
        this.ticketRepo = ticketRepo;
    }

    @Override
    public void saveCustomersToCsv(String fileName) throws CustomerException {
        File file = new File(fileName);
        List<Customer> customers = customerRepo.read();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("id,name,email,address,nationality,customerCategory");
            for (Customer customer : customers) {
                pw.println(
                        customer.getId() + ","
                        + customer.getName() + ","
                        + customer.getEmail() + ","
                        + customer.getAddress() + ","
                        + customer.getNationality() + ","
                        + customer.getCategory());
            }
        } catch (FileNotFoundException ex) {
            throw new CustomerException(CustomerExceptionCodes.FILE_NOT_ACCESSIBLE);
        }
    }

    @Override
    public int readCustomerFromCsv(String fileName) throws CustomerException {
        File file = new File(fileName);
        int rowsRead = 0;
        try ( Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] words = line.split(",");
                    Customer customer = new Customer();
                    customer.setId(Integer.parseInt(words[0].trim()));
                    customer.setName(words[1].trim());
                    customer.setEmail(words[2].trim());
                    customer.setAddress(words[3].trim());
                    customer.setNationality(words[4].trim());
                    customer.setCategory(CustomerCategory.valueOf(words[5].trim()));
                    customerRepo.create(customer);
                    rowsRead++;
                } catch (NumberFormatException e) {
                    throw new CustomerException(CustomerExceptionCodes.INVALID_DATA);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new CustomerException(CustomerExceptionCodes.FILE_NOT_ACCESSIBLE);
        }

        return rowsRead;
    }

    @Override
    public void saveItineraryToCsv(String fileName) throws ItineraryException {
        File file = new File(fileName);
        List<Itinerary> itineraries = itineraryRepo.read();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("id,departure,destination,date,airline,price");
            for (Itinerary itinerary : itineraries) {
                pw.println(
                        itinerary.getId() + ","
                        + itinerary.getDeparture() + ","
                        + itinerary.getDestination() + ","
                        + (itinerary.getDepartureDate().getYear() + 1900) + "-"
                        + (itinerary.getDepartureDate().getMonth() + 1) + "-"
                        + (itinerary.getDepartureDate().getDate()) + " "
                        + (itinerary.getDepartureDate().getHours()) + ":"
                        + (itinerary.getDepartureDate().getMinutes()) + ","
                        + itinerary.getAirline() + ","
                        + itinerary.getPrice()
                );
            }
        } catch (FileNotFoundException ex) {
            throw new ItineraryException(ItineraryExceptionCodes.FILE_NOT_ACCESSIBLE);
        }
    }

    @Override
    public int readItineraryFromCsv(String fileName) throws ItineraryException {
        File file = new File(fileName);
        int rowsRead = 0;
        try ( Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] words = line.split(",");
                    Itinerary itinerary = new Itinerary();
                    itinerary.setId(Integer.parseInt(words[0].trim()));
                    itinerary.setDeparture(AirportCode.valueOf(words[1].trim()));
                    itinerary.setDestination(AirportCode.valueOf(words[2].trim()));
                    String[] dateAndTimeParts = words[3].trim().split(" ");
                    String[] dateParts = dateAndTimeParts[0].split("-");
                    String[] timeParts = dateAndTimeParts[1].split(":");
                    itinerary.setDepartureDate(new Date(
                            Integer.parseInt(dateParts[0]) - 1900,
                            Integer.parseInt(dateParts[1]) - 1,
                            Integer.parseInt(dateParts[2]),
                            Integer.parseInt(timeParts[0]),
                            Integer.parseInt(timeParts[1])
                    ));
                    itinerary.setAirline(words[4].trim());
                    itinerary.setPrice(BigDecimal.valueOf(Double.parseDouble(words[5])));
                    itineraryRepo.create(itinerary);
                    rowsRead++;
                } catch (NumberFormatException e) {
                    throw new ItineraryException(ItineraryExceptionCodes.INVALID_DATA);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new ItineraryException(ItineraryExceptionCodes.FILE_NOT_ACCESSIBLE);
        }
        return rowsRead;
    }

    @Override
    public void saveTicketToCsv(String fileName) throws TicketException {
        File file = new File(fileName);
        List<Ticket> tickets = ticketRepo.read();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("id,customerId,itineraryId,paymentMethod,paymentAmount");
            for (Ticket ticket : tickets) {
                pw.println(
                        ticket.getId() + ","
                        + ticket.getCustomerId() + ","
                        + ticket.getItineraryId() + ","
                        + ticket.getPaymentMethod() + ","
                        + ticket.getPaymentAmount());
            }
        } catch (FileNotFoundException ex) {
            throw new TicketException(TicketExceptionCodes.FILE_NOT_ACCESSIBLE);
        }
    }

    @Override
    public int readTicketFromCsv(String fileName) throws TicketException {
        File file = new File(fileName);
        int rowsRead = 0;
        try ( Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Ticket ticket = new Ticket();
                    String[] words = line.split(",");
                    ticket.setId(Integer.parseInt(words[0].trim()));
                    ticket.setCustomerId(Integer.parseInt(words[1].trim()));
                    ticket.setItineraryId(Integer.parseInt(words[2].trim()));
                    ticket.setPaymentMethod(PaymentMethod.valueOf(words[3].trim()));
                    ticket.setPaymentAmount(BigDecimal.valueOf(Double.parseDouble(words[4].trim())));
                    ticketRepo.create(ticket);
                    customerRepo.read(ticket.getCustomerId()).getTicketList().add(ticket);
                    rowsRead++;
                } catch (NumberFormatException e) {
                    throw new TicketException(TicketExceptionCodes.INVALID_DATA);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new TicketException(TicketExceptionCodes.FILE_NOT_ACCESSIBLE);
        }
        return rowsRead;
    }

}
