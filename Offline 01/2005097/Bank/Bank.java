package Bank;

import Accounts.*;
import Employees.Cashier;
import Employees.ManagingDirector;
import Employees.Officer;
import LoanApproval.LoanApproval;
import LoanManagement.LoanManager;

import java.util.HashMap;
import java.util.Map;

public class  Bank extends LoanApproval
{
    private double internalFund;
    private static Bank bank;
    private Map<String, Account> accounts;
    private Officer officer1;
    private Officer officer2;
    private Cashier[] cashiers;
    private ManagingDirector managingDirector;
    private Bank()
    {
        internalFund=1000000;
        accounts=new HashMap<>();
        Cashier[] c=new Cashier[5];
        for(int i=0;i<5;i++)
        {
            c[i]=new Cashier(this);
        }
        cashiers=c;
         managingDirector=new ManagingDirector(this);
         officer1=new Officer(this);
         officer2=new Officer(this);
        System.out.println("Bank Created; MD, O1, O2, C1, C2, C3, C4, C5 created");


    }
    public static Bank getInstance() {
        if (bank == null) {
            bank = new Bank();
        }
        return bank;
    }
    public  Account createAccount(String accountHolderName,String accountType, double initialBalance)
    {

        if (accounts.containsKey(accountHolderName))
        {
            System.out.println("An account already exists for "+accountHolderName);
            return null;
        }

        if(accountType=="FixedDeposit" && initialBalance<100000)

        {
            System.out.println("Minimum initial deposite must be atleast 100000");
            return null;
        }

        Account account;
        switch (accountType)
        {
            case "Student":
                account=new StudentAccount(accountHolderName,initialBalance);
                accounts.put(accountHolderName,account);
                System.out.println("Student account for "+ account.getAccountHolderName()+ " Created; initial balance "+account.getBalance()+"$");
                return account;
            case "Savings":
                account=new SavingsAccount(accountHolderName,initialBalance);
                accounts.put(accountHolderName,account);
                System.out.println("Savings account for "+ account.getAccountHolderName()+ " Created; initial balance "+account.getBalance()+"$");
                return account;

            case "FixedDeposit":
                if(initialBalance>=100000)
                {
                    account=new FixedDepositAccount(accountHolderName,initialBalance);
                    accounts.put(accountHolderName,account);
                    System.out.println("Fixed Deposit account for "+ account.getAccountHolderName()+ " Created; initial balance "+account.getBalance()+"$");
                    return account;
                }
                else
                {
                    System.out.println("Initial Balance must be greater than 100000");
                    return  null;
                }

            default:
                System.out.println("Invalid account type");
                return null;

        }


    }
    public Account getAccount(String accountHolderName)
    {
        return accounts.get(accountHolderName);
    }
    public void  createAccount(Account account)
    {
        accounts.put(account.getAccountHolderName(), account);
    }

    public double getInternalFund() {
        return internalFund;
    }


    public Cashier[] getCashiers() {
        return cashiers;
    }

    public ManagingDirector getManagingDirector() {
        return managingDirector;
    }

    public Officer getOfficer1() {
        return officer1;
    }

    public Officer getOfficer2() {
        return officer2;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setInternalFund(double internalFund) {
        this.internalFund = internalFund;
    }
}