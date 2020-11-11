import java.util.ArrayList;
import java.util.List;
public class MessageManager {
//    private
    private int sender_id;

    public void setSender_id(int i) {
        this.sender_id = i;
    }

    public void message_allusers(Event event, String content, DataBase d){
        for (int receiver_id : event.getSingned_userid()){
            Message m = new Message(content, this.sender_id, receiver_id);
            d.messagelist.add(m);
        }
    }

    public String message_oneuser(Event event_id, User receiver,String content, DataBase d){
        if (event.getSingned_userid().contains(receiver.getUser_id())) {
            Message m = new Message(content, this.sender_id, receiver.getUser_id());
            d.messagelist.add(m);
            return "success";
        }else{
            return "unsuccess";
        }
    }

    public void message_specific_user(Event event, User receiver, String content){
        Message m = new Message(content, this.sender, receiver);
        //这一行是用来把message加入到对应receiver的database
    }

    public void message_speaker(Event event, String content){
        Message m = new Message(content, this.sender, event.getSpeaker());
        //这一行是用来把message加入到对应receiver的database
    }

    public void reply_message(Message message, String content){
        Message m = new Message(content, message.getReceiver(), message.getSender());
        //这一行是用来把message加入到对应receiver的database
    }


}
//    read messages related with current user from database and create a list of them.
//        message all user signed up for one event.
//        message one user signed up for one event.
//        message one specific user.
//        message the speaker of one event.
//        reply message.
