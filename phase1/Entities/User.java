import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private int user_id;
    private String password;
    private List<String> friends;
//    private List<String> user_id;
//    private List<String> password;
//    private boolean bilingual;

    public User(int id, String passw){
        this.user_id = id;
        this.password = passw;
        this.friends = new ArrayList<>();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFriends(List<String> add_friends){
        for(int x = 0; x < add_friends.size(); x++){
            boolean flag = false;
            for(int y = 0; y < friends.size(); x ++){
                if (add_friends.get(x) == friends.get(y)){
                    flag = true;
                }
            }
            if (flag == false){
                friends.add(add_friends.get(x));
            }
        }
    }

    public int getUser_id() {
        return user_id;
    }

    public List<String> getFriends() {
        return friends;
    }

//    public void Message_fri(List<String> add_friends)
//    {
//        for(int x = 0; x < add_friends.size(); x++){
//            boolean flag = false;
//            for(int y = 0; y < friends.size(); x ++){
//                if (add_friends.get(x) == friends.get(y)){
//                    flag = true;
//                }
//                if (flag == false){
//                    friends.add(add_friends.get(x));
//                }
//            }
//        }
//    }
}