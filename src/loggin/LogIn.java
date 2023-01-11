package loggin;

import security.Encryption;
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
        final boolean response = ValidUserToLogIn.isIdValid(ID)
                .and(ValidUserToLogIn.isListEmpty())
                .test(userList);
        if(!response && ValidUserToLogIn.notInPasswords(PASSWORD)) throw new NoSuchUserException("Invalid User Or Password");
        return response;

    }

    private interface ValidUserToLogIn extends Predicate<List<User>> {
        static boolean notInPasswords(String passwordToSearch){
            return Encryption.getEncryptionTable().get(passwordToSearch) == null;
        }

        static ValidUserToLogIn isIdValid(String IdToSearch){
            return users -> users.stream()
                    .map(User::getID)
                    .anyMatch(ID -> ID.equals(IdToSearch));
        }

        static ValidUserToLogIn isListEmpty(){
            return List::isEmpty;
        }

        default ValidUserToLogIn and(ValidUserToLogIn anotherValidator){
            return users -> this.test(users) ? anotherValidator.test(users) : this.test(users);
        }
    }
}