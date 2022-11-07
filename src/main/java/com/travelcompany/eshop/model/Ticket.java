package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.PaymentMethod;
import java.math.BigDecimal;

public class Ticket extends PersistentClass {

    private int customerId;
    private int itineraryId;
    private PaymentMethod paymentMethod;
    private BigDecimal paymentAmount;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}
