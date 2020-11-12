import java.util.ArrayList;
import java.util.List;

//!!!ADD FUNCTION NAMES IF YOU THINK NECESSARY!!!!!!
// FEEL FREE TO ADD THEM!!! THANKSSSS :)

public class UserManager {

    public void createSpeaker(String password, String name, DataBase d){
        Speaker s = new Speaker(d.getNextUserId(), password, name);
        d.addUser(s); //汤zheng，jim， 这里是不是应该也在database里面加上一个类似于addMessage（）的方法？
    }

    // 檢查是否能addEventToSpeaker
    // 如果沒有情況是不能新增event to speaker那把method刪掉就好
    // jenna & Lihang 麻煩你們implement了
    public boolean canAddEventToSpeaker(){}

    public void addEventToSpeaker(int eventId, int speakerId, DataBase d){
        d.getSpeakerById(speakerId).addGivingEvent(eventId);
    }

    // 有情況是attendee不能加這個event的嗎？如有，得有個canAddEventToAttendee的method -grace(controller)
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

    // 加了這個method，jenna & Lihang 可以檢查看看有沒有錯嗎？ -grace
    public List<Integer> getSpeakerEventList(int speakerID, DataBase d){
        return d.getSpeakerById(speakerID).get_GivingEventList();
    }
}