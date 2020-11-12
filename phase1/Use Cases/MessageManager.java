import java.util.ArrayList;
import java.util.List;
public class MessageManager {
//    private
    private int senderId;

    public void setSender_id(int i) {
        this.senderId = i;
    }

    public void message_allusers(int eventId, String content, DataBase d){
        for (int receiver_id : d.getEventById(eventId).getSingned_userid()){
            Message m = new Message(content, this.senderId, receiver_id);
            d.addMessage(m);
        }
    }

    public void message_oneuser(int eventId, int receiverId,String content, DataBase d){
        if (d.getEventById(eventId).getSingned_userid().contains(receiverId)) {
            Message m = new Message(content, this.senderId, receiverId);
            d.addMessage(m);
        }
    }

    public void message_specific_user(int receiverId, String content, DataBase d){
        Message m = new Message(content, this.senderId, receiverId);
        d.addMessage(m);
        //我这个没有办法判断sender是否可以发送消息给receiver，因为sender没有一个friedn list
    }

    public void message_speaker(int eventId, String content, DataBase d){
        Message m = new Message(content, this.senderId, d.getEventById(eventId).getSpeakerId());
        d.addMessage(m);
        //database 中没有直接能通过eventId来得到speaker的方法，所以我先通过eventId找到对应的event，再通过event找到speaker
    }

    public void reply_message(int receiverId, String content, DataBase d){
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
