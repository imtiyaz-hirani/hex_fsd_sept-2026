package com.beans;

public class Savings implements Account{
    @Override
    public double getInterestRate() {
        return 4.0;
    }

    public void myMethod(){
        System.out.println("my logic");
    }
}
