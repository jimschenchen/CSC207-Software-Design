package usecase;

import java.util.ArrayList;
import java.util.List;
import entity.*;
import gateway.GatewayFacade;
public class MessageManager {

    public void messageAllUsers(int eventId, int senderId, String content, GatewayFacade g){
        /**
         * @Description: message all signed up users in an event
         */
        for (int receiverId : g.getEventById(eventId).getSignedUpUserList()){
            Message m = new Message(content, senderId, receiverId);
            g.addMessage(m);
        }
    }

    public void messageAllUsersInAllSpeakingEvents(int speakerID, String content, GatewayFacade g){
        /**
         * @Description: message all signed up users in multiple speaking events
         */
        Speaker speaker = g.getSpeakerById(speakerID);
        List<Integer> speakingEvents = speaker.get_GivingEventList();
        for (int eventID : speakingEvents){
            messageAllUsers(eventID, speakerID, content, g);
        }
    }

    public boolean canMessageAttendeeOfEvent(int eventId, int receiverId, GatewayFacade g){
        /**
         * @Description: judge whether receiver whose Id is receiverID participate Event which id is eventId
         */
        return g.getEventById(eventId).getSignedUpUserList().contains(receiverId);
    }

    public void messageOneUser(int senderId, int receiverId, String content, GatewayFacade g){
        /**
         * @Description: send message to one user whose id is receiverId
         */
        Message m = new Message(content, senderId, receiverId);
        g.addMessage(m);
    }


    public boolean canMessageSpeaker(int senderId, int receiverId, GatewayFacade g){
        /**
         * @Description: judge whether a User whose Id is senderId is eligible to send a message to a Speaker whose
         * id is receiverId
         */
        boolean senderCheck = g.getOrganizerById(senderId) != null || g.getAttendeeById(senderId) != null;
        return senderCheck && g.getSpeakerById(receiverId) != null;
    }

    public boolean canMessageAttendee(int senderId, int receiverId, GatewayFacade g){
        /**
         * @Description: judge whether a User whose Id is senderId is eligible to send a message to a Attendee whose
         * id is receiverId
         */
        boolean senderCheck = g.getOrganizerById(senderId) != null || g.getAttendeeById(senderId) != null;
        boolean ishimself = senderId == receiverId;
        return (!ishimself) && senderCheck && g.getAttendeeById(receiverId) != null
                && g.getOrganizerById(receiverId) == null;
    }

    public boolean canMessageAllSpeakersOrAllAttendee(int senderId, GatewayFacade g){
        /**
         * @Description: judge whether a User is an Organizer
         */
        return g.getOrganizerById(senderId) != null;
    }

    public void messageAllSpeakers(String content, int senderId, GatewayFacade g) {
        /**
         * @Description: message all Speaker
         */
        List<User> users = g.getUserList();
        for (User user : users){
            if (user instanceof Speaker){
                messageOneUser(senderId, user.getUserId(), content, g);
            }
        }
    }

    public void messageAllAttendees(int senderId, String content, GatewayFacade g){
        /**
         * @Description: message all Attendee
         */
        List<User> users = g.getUserList();
        for (User user : users){
            if (user instanceof Attendee & !(user instanceof Organizer)){
                messageOneUser(senderId, user.getUserId(), content, g);
            }
        }
    }

    /**
     * Get formatted string of a received message
     * @param message the message wanted
     * @return String of the message in format "From: xxx Content: yyy"
     */
    private String getStringOfReceivedMessage(Message message, GatewayFacade g){
        return "From: " + g.getUserById(message.getSenderId()).getUserName() +
                " Content: " + message.getInfo();
    }

    /**
     * Get formatted string of a sent message
     * @param message the message wanted
     * @return String of the message in format "To: xxx Content: yyy"
     */
    private String getStringOfSentMessage(Message message, GatewayFacade g){
        return "To: " + g.getUserById(message.getReceiverId()).getUserName() +
                " Content: " + message.getInfo();
    }

    public List<String> getReceivedMessageListByUserId(int userID, GatewayFacade g){
        /**
         * @Description: get all Messages which received by a User whose id is userID
         */
        List<Message> messages = g.getReceivedMessageListByUserId(userID);
        List<String> sMessages = new ArrayList<>();
        for (Message message : messages){
            sMessages.add(getStringOfReceivedMessage(message, g));
        }
        return sMessages;
    }

    public List<String> getSentMessageListByUserId(int userID, GatewayFacade g) {
        /**
         * @Description: get all Messages which send by a User whose id is userID
         */
        List<Message> messages = g.getSentMessageListByUserId(userID);
        List<String> sMessages = new ArrayList<>();
        for (Message message : messages){
            sMessages.add(getStringOfSentMessage(message, g));
        }
        return sMessages;
    }

    public boolean canReplyMessage(int currentUserID, int positionOfMessage, GatewayFacade g){
        /**
         * @Description: judge whether a User whose id is currentuserID is eligible to reply a message which id is
         * positionOfMessage
         */
        Message message = g.getReceivedMessageListByUserId(currentUserID).get(positionOfMessage);
        int senderID = message.getSenderId();
        return g.getOrganizerById(senderID) == null;
    }

    public void replyMessage(String content, int currentUserId, int positionOfMessage, GatewayFacade g) {
        /**
         * @Description: reply a message which id is positionOfMessage
         */
        Message m = g.getReceivedMessageListByUserId(currentUserId).get(positionOfMessage);
        Message reply = new Message(content, m.getReceiverId(), m.getSenderId());
        g.addMessage(reply);
    }


}