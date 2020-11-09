import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private int user_id;
    private String user_name;
    private static int u_id;
    private String password;
    private List<User> friends;

    public User(String passw, String name){
        this.user_name = name;
        this.user_id = u_id;
        u_id ++;
        this.password = passw;
        this.friends = new ArrayList<>();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void add_new_friends(User new_user){
        this.friends.add(new_user);
    }


    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public List<User> getFriends() {
        return friends;
    }
}