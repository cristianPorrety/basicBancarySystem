package loggin;

import user.User;

import java.util.*;
import java.util.function.Predicate;

public class LogIn {
    private static final Scanner in = new Scanner(System.in);
    public static boolean login(List<User> userList) throws NoSuchUserException {
        System.out.print("write your ID: ");
        final String ID = in.nextLine();
        System.out.print("write your Password: ");
        final String PASSWORD = in.nextLine();
        final boolean response = ValidUser.isIdValid(ID).and(ValidUser.isPasswordValid(PASSWORD)).test(userList);
        if(response) return response;
        else throw new NoSuchUserException("Invalid User Or Password");
    }

    private interface ValidUser extends Predicate<List<User>> {
        static ValidUser isIdValid(String IdToSearch){
            return users -> users.stream()
                    .map(User::getID)
                    .anyMatch(ID -> ID.equals(IdToSearch));
        }

         static ValidUser isPasswordValid(String PasswordToSearch){
            return users -> users.stream()
                    .map(User::getPassword)
                    .anyMatch(password -> password.equals(PasswordToSearch));
        }

        default ValidUser and(ValidUser anotherValidator){
            return users -> this.test(users) ? anotherValidator.test(users) : this.test(users);
        }
    }
}