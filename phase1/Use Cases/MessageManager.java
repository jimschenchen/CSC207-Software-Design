import java.util.ArrayList;
import java.util.List;
public class MessageManager {
//    private
    private List<Message> message;
    private User sender;
    public MessageManager(User u){
        this.sender = user;
        this.message = ;   //关于User u 的所有message,是从database中提取出来的
                           // 我觉得message应该是以receiver的用户名作为相对应txt文件的文件名存储在database中专门储存信息的一个地方，这样方便提取出来
    }

    public void message_allusers(Event event, String content){
        for (User receiver : event.getUsers_lst()){
            Message m = new Message(content, this.sender, receiver);
            //这一行是用来把message加入到对应receiver的database
        }
    }

    public void message_oneuser(Event event, User receiver, String content){
        if (event.getUsers_lst().contains(receiver)) {
            Message m = new Message(content, this.sender, receiver);
            //这一行是用来把message加入到对应receiver的database
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
