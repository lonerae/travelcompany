package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.CustomerCategory;
import java.util.ArrayList;
import java.util.List;

public class Customer extends PersistentClass {

    private String name;
    private String email;
    private String address;
    private String nationality;
    private CustomerCategory category;
    private final List<Ticket> ticketList;

    public Customer() {
        this.ticketList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public CustomerCategory getCategory() {
        return category;
    }

    public void setCategory(CustomerCategory category) {
        this.category = category;
    }
    
    public List<Ticket> getTicketList() {
        return ticketList;
    }

}
