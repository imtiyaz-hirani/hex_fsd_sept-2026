package com.factory.design_pattern;

public class RTGS implements Payment{
    @Override
    public double transactionCharge() {
        return 50;
    }

    @Override
    public double maxTransactionLimit() {
        return 1000000;
    }
}
