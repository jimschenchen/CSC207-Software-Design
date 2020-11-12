import java.util.ArrayList;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class UserManager {

    public void createSpeaker(String password, String name, DataBase d){
        Speaker s = new Speaker(d.getNextUserId(), password, name);
        d.addUser(s); //汤zheng，jim， 这里是不是应该也在database里面加上一个类似于addMessage（）的方法？
    }

    public void addEventToSpeaker(int eventId, int speakerId, DataBase d){
        d.getSpeakerById(speakerId).addGivingEvent(eventId);
    }

    public void addEventToAttendeeOrOrganizer(int eventId, int Id, DataBase d){
        if (d.getAttendeeById(Id) == null){
            d.getOrganizerById(Id).signUpEvent(eventId);
        }else{
            d.getAttendeeById(Id).signUpEvent(eventId);
        }
    }

    public void cancelEventToAttendeeOrOrganizer(int eventId, int Id, DataBase d){
        if (d.getAttendeeById(Id) == null){
            d.getOrganizerById(Id).cancelEvent(eventId);
        }else{
            d.getAttendeeById(Id).cancelEvent(eventId);
        }
    }

    public void setPassword(int userId,String password, DataBase d) {
        d.getUserById(userId).setPassword(password);
    }

    public String getUserName(int userId,DataBase d) {
        return d.getUserById(userId).getUserName();
    }

    public List getOrganizerOrAttendeeEventList(int Id, DataBase d){
        if (d.getAttendeeById(Id) == null){
            return d.getOrganizerById(Id).getEventList();
        }else {
            return d.getAttendeeById(Id).getEventList();
        }
    }
}