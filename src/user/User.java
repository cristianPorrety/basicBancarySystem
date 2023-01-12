package user;

public class User {
    private final String ID;
    private Long cash;
    public User(String ID) { this.ID = ID; }

    public String getID() {
        return ID;
    }

    public Long getCash() {
        return cash;
    }

    public void setCash(Long cash) {
        this.cash = cash;
    }
}
