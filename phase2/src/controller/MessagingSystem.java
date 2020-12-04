package controller;

import java.util.List;
import gateway.GatewayFacade;

class MessagingSystem extends subSystem {

    /**
     * Allow user to message all attendees in an event.
     *
     * @param eventID The event that all attendees and the speaker is in.
     * @param content The content of the message.
     * @return Return true if the message is sent successfully, false when input is invalid.
     */
    boolean messageAllAttendeesInEvent(String eventID, String content, GatewayFacade gw){
        try{
            int eID = Integer.parseInt(eventID);
            if (em.isExistingEvent(eID, gw)){
                mm.messageAllUsers(eID, user, content, gw);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Allow speaker to message all users in all events speaker is speaking in.
     *
     * @param content Content of the message
     * @return Return true if the message is sent successfully, else return false.
     */
    boolean messageAllUsersInAllSpeakingEvents(String content, GatewayFacade gw){
        if (um.isExistingSpeaker(user, gw)){
            mm.messageAllUsersInAllSpeakingEvents(user, content, gw);
            return true;
        }
        return false;
    }

    /**
     * Allow user to message a specific attendee in an event
     *
     * @param eventID The event the attendees and speaker is in.
     * @param receiverID The receiver's ID.
     * @param content Content of the message.
     * @return Return true if the message is sent successfully, false when input is invalid.
     */
    boolean messageOneSpecificUserInEvent(String eventID, String receiverID, String content, GatewayFacade gw){
        try{
            int eID = Integer.parseInt(eventID);
            int reID = Integer.parseInt(receiverID);
            if (em.isExistingEvent(eID, gw) && mm.canMessageAttendeeOfEvent(eID, reID, gw)){
                mm.messageOneUser(user, reID, content, gw);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Sends a message to all speakers at once. Only Organizers can perform such action.
     *
     * @param content Content of the message.
     * @return Return true if messages are sent successfully. False if the logged in user is not an organizer.
     */
    boolean messageAllSpeakers(String content, GatewayFacade gw){
        if (mm.canMessageAllSpeakersOrAllAttendee(user, gw)){
            mm.messageAllSpeakers(content, user, gw);
            return true;
        }
        return false;
    }

    /**
     * Sends a message to a specific speaker.
     *
     * @param receiverID The receiver's (speaker) ID.
     * @param content Content of the message
     * @return Return true if the message is sent successfully. False if the input(ID) is invalid, or the user
     * is not allowed to message the speaker.
     */
    boolean messageSpeaker(String receiverID, String content, GatewayFacade gw){
        try{
            int rID = Integer.parseInt(receiverID);
            if (mm.canMessageSpeaker(user, rID, gw)){
                mm.messageOneUser(user, rID, content, gw);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Sends a message to all attendees in the system. This action can only be performed by an organizer.
     *
     * @param content Content of the message.
     * @return Return true if the messages are successfully sent. False if the logged in sender is not allowed to
     * perform this action.
     */
    boolean messageAllAttendee(String content, GatewayFacade gw){
        if(mm.canMessageAllSpeakersOrAllAttendee(user, gw)){
            mm.messageAllAttendees(user, content, gw);
            return true;
        }
        return false;
    }

    /**
     * Sends a message to an attendee.
     *
     * @param receiverID Message receiver's ID.
     * @param content Content of the message.
     * @return Return true if the message is sent successfully. False if the user is not allowed to message this
     * attendee, or input is invalid.
     */
    boolean messageAttendee(String receiverID, String content, GatewayFacade gw){
        try{
            int rID = Integer.parseInt(receiverID);
            if (mm.canMessageAttendee(user, rID, gw)){
                mm.messageOneUser(user, rID, content, gw);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Reply to a specific message.
     *
     * @param messageIndex Position of the message (to identify which message it is replying)
     * @param content Content of the replying message.
     * @return Return true when the message is successfully sent, false if the user is not allowed to reply to the
     * message.
     */
    boolean replyMessage(String messageIndex, String content, GatewayFacade gw){
        try{
            int mIndex = Integer.parseInt(messageIndex);
            if (mm.canReplyMessage(user, mIndex, gw)){
                mm.replyMessage(content, user, mIndex, gw);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Reads the sent messages of the user currently logged in.
     *
     * @return List of Strings representing the messages the user sent.
     */
    List<String> readSentMessages(GatewayFacade gw){
        return mm.getSentMessageListByUserId(user, gw);
    }

    /**
     * Reads the incoming messages of the user currently logged in.
     *
     * @return List of Strings representing the messages the user reveived.
     */
    List<String> readReceivedMessages(GatewayFacade gw){
        return mm.getReceivedMessageListByUserId(user, gw);
    }

}
