package entity;

public class VIP extends User {
    /**
     * Constructor for the user object
     *
     * @param uid      the user id
     * @param password the password
     * @param name     the user name
     */
    private boolean isVIP;

    public VIP(int uid, String password, String name) {
        super(uid, password, name);
        this.isVIP = true;
    }

    public void NotVIP(){
        this.isVIP = false;
    }

    public boolean checkVIP(){
        return this.isVIP;
    }
}
