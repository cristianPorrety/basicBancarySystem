package bankAccount.loan;

import assestment.Validations;
import bankAccount.BankAccount;
import home.Home;
import loggin.GeneralSignUp;
import loggin.LogIn;
import loggin.NoSuchUserException;
import security.Encryption;
import user.User;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class LoanAccount extends BankAccount {

    @Override
    public void signUp() throws NoSuchUserException {
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
            preSign.setCash(secureLoan(Long.parseLong(JOptionPane.showInputDialog("how much money do you want?"))));
            final String password = JOptionPane.showInputDialog("type your password");
            passwordTable.put(password, Encryption.getSaltvalue(30));
            USERS.put(passwordTable.get(password), preSign);
            intoAcount(preSign);
        }
    }

    @Override
    protected void intoAcount(User user) throws NoSuchUserException {
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
                moneyToPay = Validations.secureReduction(moneyToPay, in.nextLong());
            }else if(!(action.equalsIgnoreCase("s"))){
                System.out.println("action unavaliable");
            }
        }while(!(action.equalsIgnoreCase("s")));
        Home.homeQuotes();
    }

    @Override
    public void login() throws NoSuchUserException {
        intoAcount(LogIn.login(USERS, passwordTable));
    }

    private Long secureLoan(Long toLoan){
        Scanner in = new Scanner(System.in);
        while (toLoan < 1){
            System.out.println("demasiado bajo para prestamo, algo mas alto: ");
            toLoan = in.nextLong();
        }
        return toLoan;
    }
}
