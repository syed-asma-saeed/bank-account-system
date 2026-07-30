package com.bank.services;

import com.bank.accounts.Account;
import com.bank.accounts.CurrentAccount;
import com.bank.accounts.SavingsAccount;
import com.bank.exceptions.AccountNotFoundException;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAmountException;

import java.util.HashMap;
import java.util.Map;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();
    private int counter = 1000;  //to generate ids

    public String createAccount(String type, String ownerName, double initialBalance) {
        counter++;
        String ID = "ACC" + counter;
        Account account;

        if ("savings".equalsIgnoreCase(type)) {
            accounts.put(ID, new SavingsAccount(ID, ownerName, initialBalance));
        } else {
            accounts.put(ID, new CurrentAccount(ID, ownerName, initialBalance));
        }

        return ID;
    }

    public void deposit(String accountId, double amount) throws AccountNotFoundException {
        Account account = accounts.get(accountId);

        if (account == null) {
            throw new AccountNotFoundException("Account not found with the given ID.");
        }
        account.deposit(amount);
    }

    public void withdraw(String accountId, double amount) throws AccountNotFoundException, InsufficientFundsException {
        Account account = accounts.get(accountId);

        if (account == null) {
            throw new AccountNotFoundException("Account not found with the given ID.");
        }
        account.withdraw(amount);
    }


    public void transfer(String fromId, String toId, double amount) throws InsufficientFundsException, InvalidAmountException {
        Account fromAccount = accounts.get(fromId);
        Account toAccount = accounts.get(toId);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    public void printStatement(String accountId) throws AccountNotFoundException {
        Account acc = accounts.get(accountId);
        if (acc == null) {
            throw new AccountNotFoundException("Account not found with the given ID");
        }
        acc.printStatement();
    }

    public Account getAccount(String accountId)
            throws AccountNotFoundException {
        Account acc = accounts.get(accountId);
        if (acc == null) throw new AccountNotFoundException("No account found: " + accountId);
        return acc;
    }
}