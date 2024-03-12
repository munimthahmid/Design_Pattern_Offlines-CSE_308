package Accounts;

import Bank.Bank;

public class StudentAccount extends  Account
{
    private static double interest_rate=5;
    public StudentAccount(String accountHolderName, double initialBalance) {
        super(accountHolderName, initialBalance);
        super.account_type="Student";
    }

    @Override
    public void deposit(Bank bank, double amount) {
        setBalance(getBalance()+amount);
        System.out.println(amount+"$ deposited; current balance "+getBalance()+"$");

        changeInternalFund(bank,amount);
    }

    @Override
    public void withdraw(Bank bank,double amount) {
        if (amount>=10000)
        {
            System.out.println("Invalid Transaction. Current balance "+getBalance()+"$");
            return;
        }
        double newBalance=getBalance()-amount;
        setBalance(newBalance);

        changeInternalFund(bank,-amount);

    }

    public static void setInterest_rate(double interest_rate) {
        StudentAccount.interest_rate = interest_rate;
    }

    public static double getInterest_rate() {
        return StudentAccount.interest_rate;
    }
}

