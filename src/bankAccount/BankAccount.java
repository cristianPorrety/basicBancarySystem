package bankAccount;

import loggin.NoSuchUserException;
import user.User;

public interface BankAccount {
    void signUp();
    void intoAcount(User user);
    void login() throws NoSuchUserException;
}
