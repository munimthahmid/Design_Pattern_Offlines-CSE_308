package LoanApproval;

import Bank.Bank;
import LoanManagement.LoanManager;

public class StudentLoanApproval extends LoanApproval {
     double loanlimit=1000;
     String type="Student";


    @Override
    public  String getType() {
        return type;
    }

    public  double getLimit() {
        return loanlimit;
    }
}
