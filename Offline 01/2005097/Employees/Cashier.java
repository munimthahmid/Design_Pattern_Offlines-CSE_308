package Employees;

import Accounts.Account;
import Bank.Bank;

import java.util.Map;

public class Cashier extends Employees {

        Bank bank;
        public Cashier(Bank bank)
        {
            super(bank);
            this.bank=bank;
        }
    @Override
    public void approveLoan() {
        // Cashiers cannot approve loans
        System.out.println("Permission denied. Cashiers cannot approve loans.");
    }

    @Override
    public void changeInterestRate(String type, double newInterestRate) {
        System.out.println("You don’t have permission for this operation");

    }

    @Override
    public void seeInternalFund(Bank bank) {
        System.out.println("You don’t have permission for this operation");

    }

}