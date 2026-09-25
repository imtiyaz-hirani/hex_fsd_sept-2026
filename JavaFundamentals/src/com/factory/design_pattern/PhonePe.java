package com.factory.design_pattern;

public class PhonePe extends UPI{
    @Override
    public double transactionCharge() {
        return 1;
    }

    @Override
    public double maxTransactionLimit() {
        return 100000;
    }
}
