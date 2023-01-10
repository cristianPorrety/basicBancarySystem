package user;

public class User {
    private final String ID;

    private String password;
    private Long cash;
    public User(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public Long getCash() {
        return cash;
    }

    public void setCash(Long cash) {
        this.cash = cash;
    }

    public void changuePassword(String PASSWORD) {
        this.password = PASSWORD;
    }
}
