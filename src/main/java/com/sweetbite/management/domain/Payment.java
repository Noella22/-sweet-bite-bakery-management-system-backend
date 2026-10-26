package com.sweetbite.management.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String paymentMethod;
    private String reference;
    private int amount;
    private LocalDate paidDate;

    @ManyToOne
    @JoinColumn(name = "cake_order_id")
    private CakeOrder cakeOrder;

    public UUID getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public CakeOrder getCakeOrder() {
        return cakeOrder;
    }

    public void setCakeOrder(CakeOrder cakeOrder) {
        this.cakeOrder = cakeOrder;
    }
}