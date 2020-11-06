import java.util.ArrayList;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class UserManager {
    private User user;

    public UserManager(User a_user){
        this.user = a_user;
    }

    public void setFriends(List<User> user_list){
        for(int x = 0; x < user_list.size(); x++){
            boolean flag = false;
            for(int y = 0; y < user.getFriends().size(); y ++){
                List<User> a = user.getFriends();
                if (user_list.get(x).getUser_id() == a.get(y).getUser_id()){
                    flag = true;
                }
            }
            if (flag == false){
                user.new_friends(user_list.get(x));
            }
        }
    }
    }