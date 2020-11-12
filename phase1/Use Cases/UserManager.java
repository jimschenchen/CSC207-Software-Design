import java.util.ArrayList;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class UserManager {

    public void createSpeaker(String password, String name, DataBase d){
        Speaker s = new Speaker(d.getNextUserId(), password, name);
        d.addSpeaker(s); //汤zheng，jim， 这里是不是应该也在database里面加上一个类似于addMessage（）的方法？
    }

    public void addEventToSpeaker(int eventId, int speakerId, DataBase d){
        d.getUserById(speakerId).addGivingEvent(eventId);
    }

    public void addEventToAttendee(int eventId, int attendeeId, DataBase d){
        d.getUserById(attendeeId).signUpEvent(eventId);
    }

    public void cancelEventToAttendee(int eventId, int attendeeId, DataBase d){
        d.getUserById(attendeeId).cancelEvent(eventId);
    }

    public void setPassword(int userId,String password, DataBase d) {
        d.getUserById(userId).setPassword(password);
    }

    public String getUserName(int userId,DataBase d) {
        return d.getUserById(userId).getUserName();
    }

    public List getAttendeeEventList(int attendeeId, DataBase d){
        return d.getUserById(attendeeId).getEventList();
    }
}