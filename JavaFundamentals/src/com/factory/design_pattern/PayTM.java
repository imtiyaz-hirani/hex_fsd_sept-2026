package com.factory.design_pattern;

public class PayTM extends UPI{
    @Override
    public double transactionCharge() {
        return 2;
    }

    @Override
    public double maxTransactionLimit() {
        return 50000;
    }
}
