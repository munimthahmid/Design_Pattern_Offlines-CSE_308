package Employees;

import Accounts.Account;
import Bank.Bank;
import LoanApproval.*;
import LoanManagement.LoanManager;

import java.util.HashMap;
import java.util.Map;

public class Officer extends Employees {
    LoanManager loanManager;
    Bank bank;


    public Officer(Bank bank,LoanManager loanManager)
    {
        super(bank);
        this.bank=bank;

    }
    public  Officer(Bank bank)
    {
        super(bank);
        this.bank=bank;
        this.loanManager=null;
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
    @Override
    public void changeInterestRate(String type, double newInterestRate) {
        System.out.println("You don’t have permission for this operation");

    }

    @Override
    public void seeInternalFund(Bank bank) {
        System.out.println("You don’t have permission for this operation");

    }

}
