package bankAccount.loan;

import bankAccount.BankAccount;
import loggin.GeneralSignUp;
import loggin.LogIn;
import loggin.NoSuchUserException;
import security.Encryption;
import user.User;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoanAccount implements BankAccount {
    private static final Map<String, User> USERS = new HashMap<>();
    private static final Map<String, String> passwordTable = new HashMap<>();
    public static Map<String, User> getUsers(){
        return USERS;
    }

    @Override
    public void signUp() {
        User preSign = GeneralSignUp.addingUser();
        if(!(
                UserIsAvaliableToLoan
                        .isNeighborhoodRangeAvaliable(Integer.parseInt(JOptionPane.showInputDialog("write your range neighboorhood status")))
                && UserIsAvaliableToLoan
                        .salaryAvaliable(Long.parseLong(JOptionPane.showInputDialog("write your salary")))
                && UserIsAvaliableToLoan
                        .childrenNumAvaliable(Integer.parseInt(JOptionPane.showInputDialog("how many children do you have"))))
        ){
            System.out.println("loan not avaliable");
        }else{
            preSign.setCash(Long.parseLong(JOptionPane.showInputDialog("how much money do you want?")));
            final String password = JOptionPane.showInputDialog("type your password");
            passwordTable.put(password, Encryption.getSaltvalue(30));
            USERS.put(passwordTable.get(password), preSign);
            intoAcount(preSign);
        }
    }

    @Override
    public void intoAcount(User user) {
        Scanner in = new Scanner(System.in);
        long moneyToPay = user.getCash() + (user.getCash() * 10 / 100);
        String action;
        do{
            System.out.println("money to pay: -" + moneyToPay);
            System.out.println("pay(p)");
            System.out.println("logout(s)");
            action = in.nextLine();
            if(action.equalsIgnoreCase("p")){
                System.out.print("how much money do you want to pay");
                moneyToPay -= in.nextLong();
            }else if(!(action.equalsIgnoreCase("s"))){
                System.out.println("action unavaliable");
            }
        }while(!(action.equalsIgnoreCase("s")));
    }

    @Override
    public void login() throws NoSuchUserException {
        intoAcount(LogIn.login(USERS, passwordTable));
    }
}
