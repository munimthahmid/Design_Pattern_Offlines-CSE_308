package Accounts;
import Bank.Bank;
import LoanApproval.LoanApproval;
import LoanManagement.LoanManager;
import Transactions.Transaction;

public abstract class Account extends LoanManager implements Transaction {
    private  String accountHolderName;
    private double balance;
    private int account_age;
    String account_type;



    public String getAccountHolderName()
    {
        return  accountHolderName;
    }
    public  double getBalance()
    {

        return balance;
    }
    protected void changeInternalFund(Bank bank,double amount)
    {
        bank.setInternalFund(amount);
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
    protected Account(String accountHolderName,double initialBalance)
    {
        this.accountHolderName=accountHolderName;
        this.balance=initialBalance;
        this.account_age=0;
    }


    public int getAccount_age() {
        return account_age;
    }

    public String getAccount_type() {
        return account_type;
    }
    public void query(Bank bank)
    {
        if (bank.getTotalLoan()!=null && bank.getTotalLoan().containsKey(this))
        {
            double  loanAmount=bank.getTotalLoan().get(this);
            System.out.println("Current balance "+balance+"$"+", loan "+ loanAmount+"$");
        }
        else
        {
            System.out.println("Current balance "+balance+"$");
        }

    }

    public void requestLoan(Bank bank,double loanAmount) {
        super.requestLoan(bank,this,loanAmount);
    }

    public void setAccount_age(int account_age) {
        this.account_age = account_age;
    }
}