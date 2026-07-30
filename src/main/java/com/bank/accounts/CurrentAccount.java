package com.bank.accounts;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CurrentAccount extends Account{
    private double overdraftlimit = 10000.0;

    public CurrentAccount(String accountId, String ownerName, double initialBalance){
        super(accountId, ownerName, initialBalance);
    }


    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException{
        if(amount <= 0){
            throw new InvalidAmountException("Amount Cannot be <= 0.");
        }else if(balance - amount >= -overdraftlimit){
            throw new InsufficientFundsException("Cannot withdraw as Over Draft Limit is reached.");
        }else{
            balance -= amount;
            transactions.add(new Transaction("WITHDRAWAL", amount));
        }
    }

}