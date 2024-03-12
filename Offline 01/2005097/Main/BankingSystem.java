package Main;

import Accounts.*;
import Admin.AdminUpdate;
import Bank.Bank;
import Employees.*;

import java.util.HashMap;
import java.util.Scanner;


public class BankingSystem {

    public static void main(String[] args) {
        Bank bank=Bank.getInstance();
        AdminUpdate admin=new AdminUpdate(bank);
        HashMap<String,Account> users=new HashMap<>();
        HashMap<String,Employees>employees=new HashMap<>();

        Employees md=bank.getManagingDirector();

        Employees o1=bank.getOfficer1();
        Employees o2=bank.getOfficer2();

        Employees c1=bank.getCashiers()[0];
        Employees c2=bank.getCashiers()[1];
        Employees c3=bank.getCashiers()[2];
        Employees c4=bank.getCashiers()[3];
        Employees c5=bank.getCashiers()[4];

        employees.put("MD",md);

        employees.put("O1",o1);
        employees.put("O2",o2);

        employees.put("C1",c1);
        employees.put("C2",c2);
        employees.put("C3",c3);
        employees.put("C4",c4);
        employees.put("C5",c5);










        Scanner scanner = new Scanner(System.in);

        Account currentuser = null;
        String cname=null;
        Employees currentEmployee=null;
        while (true) {

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("Exit")) {
                System.out.println("Simulation ended.");
                break;
            }

            String[] tokens = command.split(" ");
            String operation = tokens[0];

            switch (operation.toLowerCase()) {
                case "create":
                    String name=tokens[1];
                    String type=tokens[2];
                    double initialBalance=Double.parseDouble(tokens[3]);
                    currentuser=bank.createAccount(name,type,initialBalance);
                    users.put(name,currentuser);
                    cname=name;
                    break;

                case "open":
                    String currentName=tokens[1];
                    cname=currentName;

                    if (users.get(currentName)!=null)

                    {   currentuser=users.get(currentName);
                        currentEmployee=null;
                        System.out.println("Welcome back, "+currentName);
                    }
                    else if (employees.get(currentName)!=null)
                    {
                        currentEmployee=employees.get(currentName);
                        currentuser=null;


                        if(bank.getCurrentPendingLoans()!=null)
                        {
                            System.out.println(cname+" active, there are loan approvals pending");
                        }
                        else
                        {
                            System.out.println(cname+" active");
                        }
                    }

                    break;

                case "deposit":
                    double amount = Double.parseDouble(tokens[1]);
                    currentuser.deposit(bank,amount);
                    break;


                case "withdraw":
                    double withdrawAmount=Double.parseDouble(tokens[1]);
                    currentuser.withdraw(bank,withdrawAmount);
                    break;


                case "query":
                    currentuser.query(bank);
                    break;

                case "request":
                    double loanAmount = Double.parseDouble(tokens[1]);
                    currentuser.requestLoan(bank,loanAmount);
                    break;


                case "approve":
                    currentEmployee.approveLoan();
                    break;


                case "change":
                    String accountType=tokens[1];
                    double interest_rate=Double.parseDouble(tokens[2]);
                    currentEmployee.changeInterestRate(accountType,interest_rate);
                    break;


                case "lookup":
                    currentEmployee.lookup(tokens[1]);
                    break;

                case "see":
                    currentEmployee.seeInternalFund(bank);
                    break;

                case "close":

                    if (employees.get(cname)!=null)
                    {
                        System.out.println("Operations for "+cname+" closed");
                    }
                    else
                    {
                        System.out.println("Transactions Closed for "+cname);
                    }
                    break;


                case "inc":
                    admin.incrementOneYear();
                    System.out.println("1 year passed");
                    break;
                default:
                    System.out.println("Invalid command: " + command);

            }
        }


        scanner.close();
    }


}





