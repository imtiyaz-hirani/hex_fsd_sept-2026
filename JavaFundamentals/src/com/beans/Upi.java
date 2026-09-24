package com.beans;


import com.enums.PaymentMode;

import java.time.LocalDate;

public class Upi extends  Payment{

    private PaymentMode paymentMode;
    private double amount;
    private long customerId;
    private LocalDate transactionDate;

    @Override
    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount + 2;
    }

    @Override
    public void setCustomerId(long customerId) {
        // check the DB
        this.customerId = customerId;
    }

    @Override
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }


    @Override
    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public long getCustomerId() {
        return customerId;
    }

    @Override
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
// YOU: reach out to me.
// YOU: I need Gateway key to make payment
// ME: We already have that method in utility class.. check that
// YOU: i saw the method, it needs those 3 things.. do i have to give those things
// ME: yes, without them U wont be able to call that method
// Hey! Use Payment class, we already have the structure fixed
// Dog extends Animal -- Dog is a Animal
// UPI extends Payment -- UPI is a Payment