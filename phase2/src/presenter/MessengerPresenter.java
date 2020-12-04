package presenter;

import controller.MessagingSystem;
import gateway.GatewayFacade;

import java.util.List;

public class MessengerPresenter {
    MessagingSystem msgSystem = new MessagingSystem();
    IMessage msgWindow = new MessengerWindow();

    void MessageAllAttendees(String content, String eventID){
        //eventID//
        //How are we supposed to ask for an id?
        // I remember now users can message only those who are from the same event
        //Right now I am asking the user, but please change it if it doesn't fit anymore
        GatewayFacade gw = new GatewayFacade();
        boolean success = msgSystem.messageAllAttendeesInEvent(eventID, content, gw);
        //Does presenter have an access to gateway?
        msgWindow.messageSuccess(success);

    }

    void MessageOneAttendee(String content, String userID){
        GatewayFacade gw = new GatewayFacade();
        boolean success = msgSystem.messageAttendee(content, userID, gw);
        msgWindow.messageSuccess(success);
        //They are not public. How do I get access to them?
    }

    void SeeReceivedMessages(){
        GatewayFacade gw = new GatewayFacade();
        msgSystem.readReceivedMessages(gw);
    }

    void SeeSentMessages(){
        GatewayFacade gw = new GatewayFacade();
        msgSystem.readSentMessages(gw);
    }
    void MessageAllSpeakers(String content){
        GatewayFacade gw = new GatewayFacade();
        boolean success = msgSystem.messageAllSpeakers();
        msgWindow.messageSuccess(success);
    }

    void MessageOneSpeaker(String content, String speakerID){
        GatewayFacade gw = new GatewayFacade();
        boolean success = msgSystem.messageSpeaker(speakerID, content, gw);
        msgWindow.messageSuccess(success);
    }

    void MessageAllAttendeesOfSpeakerEvent(String content){
        GatewayFacade gw = new GatewayFacade();
        boolean success = msgSystem.messageAllUsersInAllSpeakingEvents(content);
        msgWindow.messageSuccess(success);
    }




}


