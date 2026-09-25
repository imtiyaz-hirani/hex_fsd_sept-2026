package com.factory.design_pattern;

public class Neft implements Payment{
    @Override
    public double transactionCharge() {
        return 10;
    }

    @Override
    public double maxTransactionLimit() {
        return 200000;
    }
}
