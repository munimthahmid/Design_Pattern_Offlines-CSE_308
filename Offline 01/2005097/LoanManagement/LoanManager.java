package LoanManagement;

import Accounts.Account;
import Bank.Bank;

import java.util.HashMap;
import java.util.Map;

public class LoanManager {
    protected Map<Account, Double> currentPendingLoans;
    protected Map<Account, Double> totalLoan;

    protected double loan_interest_rate=10;

    public LoanManager() {
        this.currentPendingLoans = new HashMap<>();
        totalLoan=new HashMap<>();
    }

    public void requestLoan( Bank bank,Account account, double loanAmount) {
        if (bank.currentPendingLoans.containsKey(account))
        {
            double existingLoanAmount = bank.currentPendingLoans.get(account);
            bank.currentPendingLoans.put(account, existingLoanAmount + loanAmount);
        }
        else
        {
            bank.currentPendingLoans.put(account, loanAmount);
        }

        System.out.println("Loan request successful, sent for approval");
    }

    public Map<Account, Double> getCurrentPendingLoans() {
        return currentPendingLoans;
    }


    public double getLoan_interest_rate() {
        return loan_interest_rate;
    }

    public Map<Account, Double> getTotalLoan() {
        return totalLoan;
    }

    public void setCurrentPendingLoans(Map<Account, Double> currentPendingLoans) {
        this.currentPendingLoans = currentPendingLoans;
    }
}
