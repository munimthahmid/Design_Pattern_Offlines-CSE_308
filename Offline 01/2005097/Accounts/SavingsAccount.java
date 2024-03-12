package Accounts;

import Bank.Bank;

public class SavingsAccount extends Account
{

    private static double interestRate=10;
    public SavingsAccount(String accountHolderName, double initialBalance) {
        super(accountHolderName, initialBalance);
        super.account_type="Savings";
    }


    @Override

    public void deposit(Bank bank,double amount) {
        setBalance(getBalance()+amount);
        System.out.println(amount+"$ deposited; current balance "+getBalance()+"$");

        changeInternalFund(bank,amount);
    }

    @Override
    public void withdraw(Bank bank,double amount) {
        double remainingBalance = getBalance() - amount;

        if (remainingBalance <= 1000) {
            System.out.println("Invalid Transaction. Current balance "+getBalance()+"$");
            return;
        }
        setBalance(remainingBalance);

        changeInternalFund(bank,-amount);
    }

    @Override
    public double getBalance() {
        return super.getBalance();
    }

    public static void setInterest_rate(double interest_rate) {
        SavingsAccount.interest_rate = interest_rate;
    }

}
