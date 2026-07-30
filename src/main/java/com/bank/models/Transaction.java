package com.bank.models;

import java.time.LocalDateTime;

public class Transaction {
    private String type;        // "DEPOSIT", "WITHDRAWAL", "TRANSFER"
    private double amount;
    private LocalDateTime timestamp;

    public void Transaction(String type, double amount){
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getType(){
        return type;
    }

    public double getAmount(){
        return amount;
    }

    public LocalDateTime getTime(){
        return timestamp;
    }

    @Override
    public String toString(){
         return "[" + type + "] Rs:" + amount +" on " +timestamp;
    }
}