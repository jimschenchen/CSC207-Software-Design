import java.util.ArrayList;
import java.util.List;
public class MessageManager {
//    private
    private int senderId;

    public void setSender_id(int i) {
        this.senderId = i;
    }

    // message all signed up users in an event
    public void message_allusers(int eventId, String content, DataBase d){
        for (int receiver_id : d.getEventById(eventId).getSingned_userid()){
            Message m = new Message(content, this.senderId, receiver_id);
            d.addMessage(m);
        }
    }

    // message specific user in an event
    public void message_oneuser(int eventId, int receiverId,String content, DataBase d){
        if (d.getEventById(eventId).getSingned_userid().contains(receiverId)) {
            Message m = new Message(content, this.senderId, receiverId);
            d.addMessage(m);
        }
    }

    // message a specific user that is not an organizer
    public boolean message_specific_user(int receiverId, String content, DataBase d){
        if (d.getUserById(receiverId) instanceof Organizer ) {
            return false;
        }
        else {
                Message m = new Message(content, this.senderId, receiverId);
                d.addMessage(m);
                return true;

        }
//        for (int i = 0; i < d.getEventList().size(); i++) {
//            if (d.getEventList().contains(receiverId) & d.getEventList().contains(senderId)) {
//                Message m = new Message(content, this.senderId, receiverId);
//                d.addMessage(m);
//                return true;
//            }
//        }
//        return false;
        //我这个没有办法判断sender是否可以发送消息给receiver，因为sender没有一个friedn list
    }


    public boolean message_speaker(String content, int reveiverId, DataBase d){
        if (! (d.getSpeakerById(reveiverId) == null)) {
            Message m = new Message(content, this.senderId, reveiverId);
            d.addMessage(m);
            return true;
        }
        else {
            return false;
        }
        }
//        Message m = new Message(content, this.senderId, d.getEventById(eventId).getSpeakerId());
//        d.addMessage(m);
        //database 中没有直接能通过eventId来得到speaker的方法，所以我先通过eventId找到对应的event，再通过event找到speaker

    public void messageAllSpeakers(String content, DataBase d) {
        for (int i = 0; i < d.getUserList().size(); i++) {
            message_speaker(content, d.getUserList().get(i).getUser_id(), d);
        }
    }


    public void reply_message(String content,int receiverId, DataBase d){
        Message m = new Message(content, this.senderId, receiverId);
        d.addMessage(m);
    }


}
//    read messages related with current user from database and create a list of them.
//        message all user signed up for one event.
//        message one user signed up for one event.
//        message one specific user.
//        message the speaker of one event.
//        reply message.
