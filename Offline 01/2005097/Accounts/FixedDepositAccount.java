package Accounts;

import Bank.Bank;

public class FixedDepositAccount extends Account

{
    private static   double interest_rate=15;

    public FixedDepositAccount(String accountHolderName, double initialBalance) {
        super(accountHolderName, initialBalance);
        super.account_type="FixedDeposit";

    }
    @Override
    public void deposit(Bank bank, double amount)
    {
        if(amount<50000)
        {
            System.out.println("Invalid Transaction. Current balance "+getBalance()+"$");
            return;
        }
        double newBalance=getBalance()+amount;

        setBalance(newBalance);
        System.out.println(amount+"$ deposited; current balance "+getBalance()+"$");


        changeInternalFund(bank,amount);
    }

    @Override
    public void withdraw(Bank bank,double amount) {
        if (getAccount_age()<1)
        {
            System.out.println("Account has not reached a maturity period of 1 year");
            return;
        }
        double newBalance=getBalance()-amount;
        setBalance(newBalance);

        changeInternalFund(bank,-amount);

    }

    public static double getInterest_rate() {
        return interest_rate;
    }

    public static void setInterest_rate(double interest_rate) {
        FixedDepositAccount.interest_rate = interest_rate;
    }
}