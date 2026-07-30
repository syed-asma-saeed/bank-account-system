package com.bank;

import com.bank.exceptions.AccountNotFoundException;
import com.bank.exceptions.InsufficientFundsException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.services.BankService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BankService bank = new BankService();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Print Statement");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            try {

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1: {

                        System.out.println("Enter 1 for Savings and 2 for Current Account:");
                        int type = scanner.nextInt();
                        scanner.nextLine();

                        if (type != 1 && type != 2) {
                            System.out.println("Invalid account type. Returning to menu.");
                            break;  // exits the switch case cleanly
                        }

                        System.out.println("Enter Owner Name:");
                        String name = scanner.nextLine();

                        System.out.println("Enter Initial Amount:");
                        double amount = scanner.nextDouble();

                        String accountId;

                        if (type == 1) {
                            accountId = bank.createAccount(
                                    "Savings",
                                    name,
                                    amount,
                                    0
                            );
                        } else {
                            System.out.println("Enter the Over Draft Limit amount for your Current Account:");
                            double limit = scanner.nextDouble();
                            accountId = bank.createAccount(
                                    "Current",
                                    name,
                                    amount,
                                    limit
                            );
                        }

                        System.out.println(
                                "Account created successfully. ID: " + accountId
                        );
                    }
                    break;


                    case 2: {

                        System.out.println("Enter Account ID:");
                        String accountId = scanner.next();  //no space

                        System.out.println("Enter Deposit Amount:");
                        double amount = scanner.nextDouble();

                        bank.deposit(accountId, amount);

                        System.out.println("Deposit successful.");
                    }
                    break;

                    case 3: {

                        System.out.println("Enter Account ID:");
                        String accountId = scanner.next();

                        System.out.println("Enter Withdrawal Amount:");
                        double amount = scanner.nextDouble();

                        bank.withdraw(accountId, amount);

                        System.out.println("Withdrawal successful.");
                    }
                    break;

                    case 4: {

                        System.out.println("Enter Sender Account ID:");
                        String fromAccount = scanner.next();

                        System.out.println("Enter Receiver Account ID:");
                        String toAccount = scanner.next();

                        System.out.println("Enter Transfer Amount:");
                        double amount = scanner.nextDouble();

                        bank.transfer(
                                fromAccount,
                                toAccount,
                                amount
                        );

                        System.out.println("Transfer successful.");
                    }
                    break;

                    case 5: {

                        System.out.println("Enter Account ID:");
                        String accountId = scanner.next();

                        bank.printStatement(accountId);
                    }
                    break;

                    case 6: {

                        System.out.println("Thank you for using Bank System.");
                        scanner.close();
                        System.exit(0);
                    }
                    break;

                    default: System.out.println("Invalid choice.");
                }


            } catch (InvalidAmountException |
                     AccountNotFoundException |
                     InsufficientFundsException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the bad input from buffer
            }
        }
    }
}