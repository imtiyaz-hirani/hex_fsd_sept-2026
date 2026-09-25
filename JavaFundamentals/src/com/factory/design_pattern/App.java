package com.factory.design_pattern;

public class App {
    public static void main(String[] args) {

        try {
            Payment payment = PaymentFactory.getInstance(PaymentMode.NEFT);
            // assert payment != null;
            System.out.println(payment.transactionCharge());
            System.out.println(payment.maxTransactionLimit());
        }
        catch(RuntimeException e){
            System.out.println("Payment failed...");
        }
    }
}
