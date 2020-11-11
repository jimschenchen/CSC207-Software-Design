import java.nio.charset.Charset;

public abstract class User {
//    we do not need friend list in Phrase 1. The uid will be counted by gateway.
    private final int uid;
    private String username;
    private String password;

    public User(int uid, String password, String name){
        this.uid = uid;
        this.username = name;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return uid;
    }

    public String getUser_name() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}