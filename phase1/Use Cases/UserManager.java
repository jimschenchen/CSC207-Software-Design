import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class UserManager {
    private User user;

    // need to change constructor to empty constructor (no need to pass any parameter to construct new UserManager)
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

    // indicate if the user can sign up for this event
    public boolean canSignUpForEvent(Event event){}

    // sign user up for event
    public void signUpForEvent(Event event){}

    // add new speaker to system (gets a Speaker object, need to add to database)
    // can pass on different input if needed. -grace
    // is there a situation where u cannot add a new speaker to the system? if so, will need to return boolean
    public void newSpeaker(Speaker speaker){}

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