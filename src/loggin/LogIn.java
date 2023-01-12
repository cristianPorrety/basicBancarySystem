package loggin;

import bankAccount.loan.LoanAccount;
import user.User;
import java.util.*;
import java.util.function.Predicate;

public class LogIn {
    private static final Scanner in = new Scanner(System.in);
    public static User login(Map<String, User> userTable, Map<String, String> passwordTable) throws NoSuchUserException {
        System.out.print("write your ID: ");
        final String ID = in.nextLine();
        System.out.print("write your Password: ");
        final String PASSWORD = in.nextLine();
        if(passwordTable.containsKey(PASSWORD)
                && ValidUserToLogIn.isIdValid(ID)
                .test(userTable
                        .values()
                        .stream()
                        .toList())
                && !userTable.isEmpty()) throw new NoSuchUserException("Invalid User Or Password");
        return LoanAccount.getUsers().get(passwordTable.get(PASSWORD));
    }

    private interface ValidUserToLogIn extends Predicate<List<User>> {
        static ValidUserToLogIn isIdValid(String IdToSearch){
            return users -> users.stream()
                    .map(User::getID)
                    .anyMatch(ID -> ID.equals(IdToSearch));
        }
    }
}