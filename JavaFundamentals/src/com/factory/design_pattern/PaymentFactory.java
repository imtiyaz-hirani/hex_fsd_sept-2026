package com.factory.design_pattern;

public class PaymentFactory {
    public static Payment getInstance(PaymentMode paymentMode) {
        return
                switch(paymentMode){
                    case NEFT -> new Neft();
                    case RTGS -> new RTGS();
                    case PhonePe -> new PhonePe();
                    case GooglePay -> new GooglePay();
                    case PayTM ->  new PayTM();
                    default -> null;
                };
        }
}
