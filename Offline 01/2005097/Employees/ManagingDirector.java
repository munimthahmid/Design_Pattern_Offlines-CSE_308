package Employees;

import Accounts.Account;
import Accounts.FixedDepositAccount;
import Accounts.SavingsAccount;
import Accounts.StudentAccount;
import Bank.Bank;
import LoanApproval.*;
import LoanManagement.LoanManager;

import java.util.HashMap;
import java.util.Map;

public class ManagingDirector  extends Employees{
    private LoanManager loanManager;
    private Bank bank;
    public ManagingDirector(Bank bank,LoanManager loanManager)
    {
        super(bank);
        this.loanManager=loanManager;
        this.bank=bank;



    }
    public ManagingDirector(Bank bank)
    {
        super(bank);
        loanManager=null;
        this.bank=bank;
    }


    @Override
    public void approveLoan() {

        if(bank.getCurrentPendingLoans().isEmpty())
        {
            System.out.println("No Loan to Approve");
            return;
        }
        LoanApproval student=new StudentLoanApproval();
        student.approveLoan(this.bank,student.getType(),student.getLimit());


        LoanApproval savings=new SavingsLoanApproval();
        savings.approveLoan(this.bank,savings.getType(),savings.getLimit());

        LoanApproval fixedDeposit=new FixedDepositLoanApproval();
        fixedDeposit.approveLoan(this.bank,fixedDeposit.getType(),fixedDeposit.getLimit());

        Map<Account, Double> newCurrentPendingLoan=new HashMap<>();
        bank.setCurrentPendingLoans(newCurrentPendingLoan);


    }

    public void changeInterestRate(String type, double newInterestRate) {
        if (type=="Student")
        {
           StudentAccount.setInterest_rate(newInterestRate);

        }
        else  if(type=="Savings")
        {
            SavingsAccount.setInterest_rate(newInterestRate);
        }
        else if (type=="FixedDeposit")
        {
            FixedDepositAccount.setInterest_rate(newInterestRate);
        }

    }

    public void seeInternalFund(Bank bank) {
        System.out.println("Internal fund of the bank is "+bank.getInternalFund());
    }
}
