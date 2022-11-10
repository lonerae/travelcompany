package com.travelcompany.eshop.dto;

import java.math.BigDecimal;

public class StatisticalDtoMaxCostCustomers {

    private String name;
    private BigDecimal maxCost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(BigDecimal maxCost) {
        this.maxCost = maxCost;
    }

}
