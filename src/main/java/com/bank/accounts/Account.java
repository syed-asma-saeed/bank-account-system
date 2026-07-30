package com.bank.accounts;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountId;
    protected String ownerName;
    protected double balance;
    protected List<Transaction> transactions;

    Account(String accountId, String ownerName, double initialBalance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount cannot be <= 0.");
        } else {
            balance += amount;
            transactions.add(new Transaction("DEPOSIT", amount));
        }
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public void printStatement() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}