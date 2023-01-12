package bankAccount.deposit;

import assestment.Validations;
import bankAccount.BankAccount;
import home.Home;
import loggin.GeneralSignUp;
import loggin.LogIn;
import loggin.NoSuchUserException;
import security.Encryption;
import user.User;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Deposit extends BankAccount {

    @Override
    public void signUp() throws NoSuchUserException {
        User toSign = GeneralSignUp.addingUser();
        final String password = JOptionPane.showInputDialog("type your password");
        passwordTable.put(password, Encryption.getSaltvalue(30));
        USERS.put(passwordTable.get(password), toSign);
        intoAcount(toSign);
    }

    @Override
    protected void intoAcount(User user) throws NoSuchUserException {
        Scanner in = new Scanner(System.in);
        long moneyDeposited = 0;
        String action;
        do {
            System.out.println("money in the badget " + moneyDeposited);
            System.out.println("deposit(d)");
            System.out.println("Get out(g)");
            System.out.println("logout(s)");
            action = in.nextLine();
            if (action.equalsIgnoreCase("d")) {
                System.out.print("how much money do you want to deposit");
                moneyDeposited = Validations.secureAdition(moneyDeposited, in.nextLong());
            } else if (action.equalsIgnoreCase("g")) {
                System.out.println("how much money do you want to get out");
                moneyDeposited = Validations.secureReduction(moneyDeposited, in.nextLong());
            } else if (!(action.equalsIgnoreCase("s"))) {
                System.out.println("action unavaliable");
            }
        } while (!(action.equalsIgnoreCase("s")));
        Home.homeQuotes();
    }

    @Override
    public void login() throws NoSuchUserException {
        intoAcount(LogIn.login(USERS, passwordTable));
    }
}
