package loggin;

import user.User;
import java.util.*;
import java.util.function.Predicate;
import static loggin.GeneralSignUp.ValidUserToSignUp.*;

public class GeneralSignUp {
    private static final Scanner in = new Scanner(System.in);
    public static User addingUser(){
        String DNI;
        String PASSWORD;
        do{
            System.out.print("type your DNI");
            DNI = in.nextLine();
            System.out.print("type your Password");
            PASSWORD = in.nextLine();
        }while(!(isValidDNI().test(DNI) && isValidPassword().test(PASSWORD)));
        return new User(DNI, PASSWORD);
    }


    interface ValidUserToSignUp extends Predicate<String>{
        static ValidUserToSignUp isValidDNI(){
            return dni -> {
                try {
                    Integer.parseInt(dni);
                }catch (NumberFormatException nfe){
                    System.out.println("your dni must be only digits");
                    return false;
                }
                return true;
            };
        }

        static ValidUserToSignUp isValidPassword(){
            return password -> {
                if(!(password.length() > 5)){
                    System.out.println("password 5 characters minimum");
                    return false;
                }
                return true;
            };
        }
    }

}
