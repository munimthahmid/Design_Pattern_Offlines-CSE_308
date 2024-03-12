package LoanApproval;

import Accounts.Account;
import Accounts.SavingsAccount;
import Bank.Bank;
import LoanApproval.LoanApproval;
import LoanManagement.LoanManager;

import java.util.Map;

public class SavingsLoanApproval extends LoanApproval {
     double loanlimit=10000;
      String type="Savings";

    @Override
    public  String getType() {
        return type;
    }

    public  double getLimit() {
        return loanlimit;
    }
}
