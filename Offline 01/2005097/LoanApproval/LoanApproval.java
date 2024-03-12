package LoanApproval;

import Accounts.Account;
import Bank.Bank;
import LoanManagement.LoanManager;


import java.security.cert.TrustAnchor;
import java.util.HashMap;
import java.util.Map;
import java.util.spi.LocaleNameProvider;

public  class LoanApproval extends  LoanManager {
     private String type;
     private double limit;


    public   void approveLoan(Bank bank,String type, double limit)
    {

        Map<Account, Double> currentPendingLoans = bank.getCurrentPendingLoans();


        if(currentPendingLoans.isEmpty()==true)
        {
            System.out.println("No Loan to Approve");
        }
        for (Map.Entry<Account, Double> entry : currentPendingLoans.entrySet()) {
            Account account = entry.getKey();
            double loanAmount = entry.getValue();
            if(account.getAccount_type()==type)
            {


                if (loanAmount <= limit)
                {

                    approveLoanForAccount(bank,account, loanAmount);
                }
                else
                {
                    System.out.println("Loan Limit exceeded for " + account.getAccountHolderName());
                }


            }
        }
    }
    public void approveLoanForAccount(Bank bank,Account account, double loanAmount) {
        account.setBalance(account.getBalance() + loanAmount);
        System.out.println("Loan for "+account.getAccountHolderName()+" approved.");


        if (bank.totalLoan!=null && bank.totalLoan.containsKey(account)) {
            bank.totalLoan.put(account, bank.totalLoan.get(account) + loanAmount);
        } else {
            bank.totalLoan.put(account, loanAmount);
        }
    }

    public Map<Account, Double> getTotalLoan() {
        return totalLoan;
    }

    public String getType() {
        return type;
    }

    public double getLimit() {
        return limit;
    }
}


