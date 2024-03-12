package Transactions;

import Bank.Bank;

public interface Transaction {
    void deposit(Bank bank, double amount);

    void withdraw(Bank bank,double amount);
}
