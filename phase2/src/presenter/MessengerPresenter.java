package presenter;

import controller.ConferenceSystem;
import controller.MessagingSystem;
import gateway.GatewayFacade;

import java.util.List;

public class MessengerPresenter extends Presenter {
    ConferenceSystem msgSystem = new ConferenceSystem();
    IMessage msgWindow = new MessengerWindow();

    void messageAllAttendees(String title, String content, String eventID){
        //eventID//
        //How are we supposed to ask for an id?
        // I remember now users can message only those who are from the same event
        //Right now I am asking the user, but please change it if it doesn't fit anymore
        boolean success = msgSystem.messageAllAttendeesInEvent(eventID, title,  content);
        //Does presenter have an access to gateway?
        msgWindow.messageSuccess(success);

    }

    void messageOneAttendee(String title, String content, String user){
        String userID = msgSystem.getUserIDbyUserName(user);
        boolean success = msgSystem.messageAttendee(userID, title, content);
        msgWindow.messageSuccess(success);
        //They are not public. How do I get access to them?
    }

   List<List<String>> readReceivedMessages(){
        return msgSystem.readReceivedMessages();
   }

    List<List<String>>  readSentMessages(){
       return msgSystem.readSentMessages();
    }


    void messageAllSpeakers(String title, String content){
        boolean success = msgSystem.messageAllSpeakers(title, content);
        msgWindow.messageSuccess(success);
    }

    void messageOneSpeaker(String title, String content, String speaker){
        String speakerID = msgSystem.getUserIDbyUserName(speaker);
        boolean success = msgSystem.messageSpeaker(speakerID, title, content);
        msgWindow.messageSuccess(success);
    }

    void messageAllAttendeesOfSpeakerEvent(String title, String content){
        boolean success = msgSystem.messageAllUsersInAllSpeakingEvents(title, content);
        msgWindow.messageSuccess(success);
    }

    void messageOneSpecificAttendee(String title, String content, String eventID, String user){
        String userID = msgSystem.getUserIDbyUserName(user);
        boolean success = msgSystem.messageOneSpecificUserInEvent(eventID, userID, title, content);
    }

    void replyTo(String content, String presenter){
        //
    }




}


