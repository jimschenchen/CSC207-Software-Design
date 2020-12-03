package entity;

public class VipUser extends Attendee{
    /**
     * Constructor for the user object
     *
     * @param uid      the user id
     * @param password the password
     * @param name     the user name
     */
    public VipUser(int uid, String password, String name) {
        super(uid, password, name);
    }
}
