package LoanApproval;

import Accounts.Account;
import Accounts.FixedDepositAccount;

import java.util.Map;


import Accounts.Account;
import LoanManagement.LoanManager;

import java.util.Map;
import  Bank.*;

public class FixedDepositLoanApproval extends LoanApproval {
    double loanlimit=100000;
    String type="FixedDeposit";

    @Override
    public String getType() {
        return type;
    }

    public double getLimit() {
        return loanlimit;
    }
}
