package com.factory.design_pattern;

public class GooglePay extends UPI{
    @Override
    public double transactionCharge() {
        return 3;
    }

    @Override
    public double maxTransactionLimit() {
        return 100000;
    }
}
