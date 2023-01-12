package loggin;
import user.User;
import java.util.*;
import java.util.function.Predicate;
import static loggin.GeneralSignUp.ValidUserToSignUp.*;

public class GeneralSignUp {
    private static final Scanner in = new Scanner(System.in);
    public static User addingUser(){
        String DNI;
        do{
            System.out.print("type your DNI");
            DNI = in.nextLine();
        }while(!(isValidDNI().test(DNI)));
        return new User(DNI);
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
    }

}
