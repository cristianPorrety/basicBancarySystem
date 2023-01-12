package bankAccount;

import loggin.NoSuchUserException;
import user.User;

import java.util.HashMap;
import java.util.Map;

public abstract class BankAccount {

    protected final Map<String, User> USERS = new HashMap<>();
    protected final Map<String, String> passwordTable = new HashMap<>();

    public abstract void signUp() throws NoSuchUserException;
    protected abstract void intoAcount(User user) throws NoSuchUserException;
    public abstract void login() throws NoSuchUserException;
}
