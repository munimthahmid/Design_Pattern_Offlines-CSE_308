package Admin;

import Accounts.Account;
import Accounts.FixedDepositAccount;
import Accounts.SavingsAccount;
import Accounts.StudentAccount;
import Bank.Bank;

import java.util.Map;

public class AdminUpdate {
    private Bank bank;
    public AdminUpdate(Bank bank)
    {
        this.bank=bank;
    }


    private void updateAccount(String type,double interest_rate,double misc) {
        Map<String, Account> accounts = bank.getAccounts();
        double loan_interest_rate = bank.getLoan_interest_rate();

        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            String accountHolderName = entry.getKey();
            Account account = entry.getValue();
            if(account.getAccount_type()==type)
            {
                double loanAmount = 0;
                if (bank.getTotalLoan().get(account) != null) {
                    loanAmount = bank.getTotalLoan().get(account);
                }

                double profit = (account.getBalance() * interest_rate) / 100;
                double loanCost = (loanAmount * loan_interest_rate) / 100;
                double finalBalance = account.getBalance() + profit - loanCost-misc;
                account.setBalance(finalBalance);

                account.setAccount_age(account.getAccount_age() + 1);
            }

        }

    }
    public void incrementOneYear()
    {
        updateAccount("Student",StudentAccount.getInterest_rate(),0);
        updateAccount("Savings", SavingsAccount.getInterest_rate(),500);
        updateAccount("FixedDeposit", FixedDepositAccount.getInterest_rate(),500);
    }
}


