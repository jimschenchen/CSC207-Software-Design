import java.util.ArrayList;
import java.util.List;
public class MessageManager {
//    private
    private int senderId;

    public void setSender_id(int i) {
        this.senderId = i;
    }

    // message all signed up users in an event
    public void message_allusers(int eventId, int senderId, String content, DataBase d){
        for (int receiver_id : d.getEventById(eventId).getSingned_userid()){
            Message m = new Message(content, senderId, receiver_id);
            d.addMessage(m);
        }
    }

    // message all signed up users in multiple speaking events
    public void messageAllUsersInAllSpeakingEvents(int speakerID, String content, DataBase db){
        Speaker speaker = db.getSpeakerById(speakerID);
        List<Integer> speakingEvents = speaker.get_GivingEventList();
        for (int eventID : speakingEvents){
            message_allusers(eventID, speakerID, content, db);
        }
    }

    public boolean canMessageAttendeeOfEvent(int eventId, int receiverId, DataBase db){
        return db.getEventById(eventId).getSingned_userid().contains(receiverId);
    }

    public void message_oneuser(int senderId, int receiverId, String content, DataBase d){
        Message m = new Message(content, senderId, receiverId);
        d.addMessage(m);
    }

    // message a specific user that is not an organizer
    public boolean message_specific_user(int senderId, int receiverId, String content, DataBase d){
        if (d.getUserById(receiverId) instanceof Organizer ) {
            return false;
        }
        else {
                Message m = new Message(content, senderId, receiverId);
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

    public boolean canMessageSpeaker(int senderId, int receiverId, DataBase db){
        boolean senderCheck = db.getOrganizerById(senderId) != null || db.getAttendeeById(senderId) != null;
        return senderCheck && db.getSpeakerById(receiverId) != null;
    }

    public boolean canMessageAttendee(int senderId, int receiverId, DataBase db){
        boolean senderCheck = db.getOrganizerById(senderId) != null || db.getAttendeeById(senderId) != null;
        return senderCheck && db.getAttendeeById(receiverId) != null;
    }

    public boolean message_speaker(String content, int senderId, int reveiverId, DataBase d){
        if (! (d.getSpeakerById(reveiverId) == null)) {
            Message m = new Message(content, senderId, reveiverId);
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

    public boolean canMessageAllSpeakers(int senderId, DataBase db){
        return db.getOrganizerById(senderId) != null;
    }

    public void messageAllSpeakers(String content, int senderId, DataBase d) {
//        for (int i = 0; i < d.getUserList().size(); i++) {
//            message_speaker(content, senderId, d.getUserList().get(i).getUser_id(), d);
//        }
        List<User> users = d.getUserList();
        for (User user : users){
            if (user instanceof Speaker){
                message_oneuser(senderId, user.getUser_id(), content, d);
            }
        }
    }

    public void messageAllAttendees(int senderId, String content, DataBase db){
        List<User> users = db.getUserList();
        for (User user : users){
            if (user instanceof Attendee){
                message_oneuser(senderId, user.getUser_id(), content, db);
            }
        }
    }


//    public void reply_message(String content, int senderId, int receiverId, DataBase d){
//        Message m = new Message(content, senderId, receiverId);
//        d.addMessage(m);
//    }

    public List<String> getReceivedMessageListByUserId(int userID, DataBase d){
        List<Message> messages = d.getReceivedMessageListByUserId(userID);
        List<String> sMessages = new ArrayList<>();
        for (Message message : messages){
            sMessages.add(message.toString());
        }
        return sMessages;
    }

    public List<String> getSentMessageListByUserId(int userID, DataBase d) {
        List<Message> messages = d.getSentMessageListByUserId(userID);
        List<String> sMessages = new ArrayList<>();
        for (Message message : messages){
            sMessages.add(message.toString());
        }
        return sMessages;
    }


    public void replyMessage(String content, int currentuserId, int positionOfMessage, DataBase d) {
        Message m = d.getReceivedMessageListByUserId(currentuserId).get(positionOfMessage);
        Message reply = new Message(content, m.getReceiverId(), m.getSenderId());
        d.addMessage(reply);
    }


}
//    read messages related with current user from database and create a list of them.
//        message all user signed up for one event.
//        message one user signed up for one event.
//        message one specific user.
//        message the speaker of one event.
//        reply message.
