package com.travelcompany.eshop.dto;

import java.math.BigDecimal;

public class StatisticalDtoTotals {

    private int totalNumberOfTickets;
    private BigDecimal totalCostOfTickets;

    public int getTotalNumberOfTickets() {
        return totalNumberOfTickets;
    }

    public void setTotalNumberOfTickets(int totalNumberOfTickets) {
        this.totalNumberOfTickets = totalNumberOfTickets;
    }

    public BigDecimal getTotalCostOfTickets() {
        return totalCostOfTickets;
    }

    public void setTotalCostOfTickets(BigDecimal totalCostOfTickets) {
        this.totalCostOfTickets = totalCostOfTickets;
    }

}
