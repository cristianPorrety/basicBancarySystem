package home;

import bankAccount.BankAccount;
import bankAccount.deposit.Deposit;
import bankAccount.loan.LoanAccount;
import loggin.NoSuchUserException;

import java.util.Scanner;

public class Home {
    public static void homeQuotes() throws NoSuchUserException {
        Scanner in = new Scanner(System.in);
        String election;
        do {
            System.out.println("what do you want:");
            System.out.println("Loan(l)");
            System.out.println("Deposit(d)");
            System.out.println("quit(q)");
            System.out.print(": ");
            election = in.nextLine();
            if (election.equalsIgnoreCase("l")){
                optionSelected(new LoanAccount(), in);
            } else if (election.equalsIgnoreCase("d")) {
                optionSelected(new Deposit(), in);
            }else if (!election.equalsIgnoreCase("q")){
                System.out.println("invalid action");
            }
        }while(!election.equalsIgnoreCase("q"));
        System.out.println("qchau");
    }

    private static void optionSelected(BankAccount election, Scanner in) throws NoSuchUserException {
        String typeInto;
        do {
            System.out.println("what do you want");
            System.out.println("login(l)");
            System.out.println("sign up(s)");
            System.out.print(":");
            typeInto = in.nextLine();
            if(typeInto.equalsIgnoreCase("l")) election.login();
            else if(typeInto.equalsIgnoreCase("s")) election.signUp();
            else System.out.println("action invalid");
        }while(true);
    }
}
