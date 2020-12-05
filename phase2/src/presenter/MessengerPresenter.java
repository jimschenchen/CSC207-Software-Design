package presenter;

import controller.ConferenceSystem;
import controller.MessagingSystem;
import gateway.GatewayFacade;

import java.util.List;

public class MessengerPresenter {
    ConferenceSystem msgSystem = new ConferenceSystem();
    IMessage msgWindow = new MessengerWindow();

    void MessageAllAttendees(String title, String content, String eventID){
        //eventID//
        //How are we supposed to ask for an id?
        // I remember now users can message only those who are from the same event
        //Right now I am asking the user, but please change it if it doesn't fit anymore
        boolean success = msgSystem.messageAllAttendeesInEvent(eventID, title, content);
        //Does presenter have an access to gateway?
        msgWindow.messageSuccess(success);

    }

    void MessageOneAttendee(String title, String content, String userID){
        boolean success = msgSystem.messageAttendee(title, content, userID);
        msgWindow.messageSuccess(success);
        //They are not public. How do I get access to them?
    }

    List<String> SeeReceivedMessages(){
        return msgSystem.readReceivedMessages();
    }

    List<String>  SeeSentMessages(){
        return msgSystem.readSentMessages();
    }
    void MessageAllSpeakers(String title, String content){
        boolean success = msgSystem.messageAllSpeakers(title, content);
        msgWindow.messageSuccess(success);
    }

    void MessageOneSpeaker(String title, String content, String speakerID){
        boolean success = msgSystem.messageSpeaker(speakerID, title, content);
        msgWindow.messageSuccess(success);
    }

    void MessageAllAttendeesOfSpeakerEvent(String title, String content){
        boolean success = msgSystem.messageAllUsersInAllSpeakingEvents(title, content);
        msgWindow.messageSuccess(success);
    }




}


