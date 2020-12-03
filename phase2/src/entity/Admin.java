package entity;

public class Admin extends User {
    /**
     * Constructor for the user object
     *
     * @param uid      the user id
     * @param password the password
     * @param name     the user name
     */
    public Admin(int uid, String password, String name) {
        super(uid, password, name);
    }
}
