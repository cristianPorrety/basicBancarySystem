package bankAccount.deposit;

import bankAccount.BankAccount;
import loggin.GeneralSignUp;
import loggin.LogIn;
import loggin.NoSuchUserException;
import security.Encryption;
import user.User;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Deposit implements BankAccount {
    private static final Map<String, User> USERS = new HashMap<>();
    private static final Map<String, String> passwordTable = new HashMap<>();
    public static Map<String, User> getUsers(){
        return USERS;
    }


    @Override
    public void signUp() {
        User toSign = GeneralSignUp.addingUser();
        final String password = JOptionPane.showInputDialog("type your password");
        passwordTable.put(password, Encryption.getSaltvalue(30));
        USERS.put(passwordTable.get(password), toSign);
        intoAcount(toSign);
    }

    @Override
    public void intoAcount(User user) {
        Scanner in = new Scanner(System.in);
        long moneyDeposited = 0;
        String action;
        do {
            System.out.println("deposit(d)");
            System.out.println("Get out(g)");
            System.out.println("logout(s)");
            action = in.nextLine();
            if (action.equalsIgnoreCase("d")) {
                System.out.print("how much money do you want to deposit");
                moneyDeposited += in.nextLong();
            } else if (action.equalsIgnoreCase("g")) {
                System.out.println("how much money do you want to get out");
                moneyDeposited -= in.nextLong();
            } else if (!(action.equalsIgnoreCase("s"))) {
                System.out.println("action unavaliable");
                System.out.println("money to pay: " + moneyDeposited);
            }
        } while (!(action.equalsIgnoreCase("s")));
    }

    @Override
    public void login() throws NoSuchUserException {
        intoAcount(LogIn.login(USERS, passwordTable));
    }
}
