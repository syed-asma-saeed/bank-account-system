package com.bank.accounts;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SavingsAccount extends Account{
    private static final double MIN_BALANCE = 1000.0;

    public SavingsAccount(String accountId, String ownerName, double initialBalance){
        super(accountId, ownerName, initialBalance);
    }


    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException{
        if(amount <= 0){
            throw new InvalidAmountException("Amount Cannot be <= 0");
        }else if(balance - amount < MIN_BALANCE){
            throw new InsufficientFundsException("Cannot withdraw due to Insufficient Funds");
        }else{
            balance -= amount;
            transactions.add(new Transaction("WITHDRAWAL", amount));
        }
    }

}