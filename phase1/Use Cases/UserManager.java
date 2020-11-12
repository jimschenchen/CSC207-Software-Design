import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class UserManager {
    private User user;

    // need to change constructor to empty constructor (no need to pass any parameter to construct new UserManager)

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

    // assume users cannot change password before login
    public void setPassword(String newPassword){
        user.setPassword(newPassword);
    }

    // login methods
    // wrote controller methods assuming:
    // 1) controller check if uid input valid
    // 2) if true, pass uid(int) and password(string) to canLogin
    // 3) canLogin match the uid and password, if match then return true, false otherwise
    // 4) if true, controller pass uid and password to usermanager login method
    // 5) login method create user (User class) and store in usermanager -grace
    public boolean canLogin(int uid, String password){}

    public void login(int uid, String password){}
}