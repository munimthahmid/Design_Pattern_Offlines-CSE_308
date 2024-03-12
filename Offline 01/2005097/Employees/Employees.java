package Employees;

import Accounts.Account;
import Accounts.FixedDepositAccount;
import Accounts.SavingsAccount;
import Accounts.StudentAccount;
import Bank.Bank;

import java.util.HashMap;
import java.util.Map;

public abstract class Employees {
   private Bank bank;

    Employees(Bank bank)
    {
        this.bank=bank;
    }


    public void lookup(String name)
    {
        Map<String, Account> accounts=this.bank.getAccounts();
        if (accounts.containsKey(name))
        {
            Account account=accounts.get(name);
            System.out.println(name+"'s current balance is "+account.getBalance()+"$");
        }
    }



    public abstract void approveLoan();
    public void changeInterestRate(String type, double newInterestRate){};
    public void seeInternalFund(Bank bank){};

}

