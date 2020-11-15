import javax.swing.*;
import java.util.List;

public class Presenter {
    private ViewModel model;
    String username;
    String id;
    String pass;
    int type;
    ConferenceSystem cs = new ConferenceSystem();

    public Presenter(ViewModel model){
        this.model = model;
    }

    public void Introduction(){
        model.Introduction();
    }

    private void CredentialsHelper(){
        String username = model.username();
        String pass = model.Pass();
        // controller needed
    }

    public void IsUserRegistered(){
        if (model.AreURegistered() == 1){
            CredentialsHelper();
            IsUserRegistered();
        }
        else{
            String[] l = model.SignIn();
            String username = l[0];
            String pass = l[1];
            this.username = username;
            this.pass = pass;
            this.id = cs.getUserIDbyUserName(username);
            EverythingCorrect();
        }
    }

    public void EverythingCorrect(){
        type = cs.login(id, pass);
        if (type != -1){
            model.Credentials(username);
        }
        else{
            model.CredentialsFail();
            IsUserRegistered();
        }
    }

    private void StringPrinter(List<String> events){
        for (String e: events){
            System.out.println(e);
        }
    }



    private void openBranchSpeaker(int answer){
        if (answer == 1){
           MessengerOpener();
        }else if (answer == 2){
            List<String> l = cs.viewSpeakingEvents();
            StringPrinter(l);
        }else {
           model.invalidInput();
        }
        MenuOpener();
    }

    private void openBranchOrganizer(int answer){
        if (answer == 1){
            MessengerOpener();
        }else if(answer == 2){
            addRoom();
        }else if(answer == 3){
            addSpeaker();
        }else if (answer == 4){
            setSpeakerEvent();
        }else if(answer == 5){
            new_event();
        }else{
            model.invalidInput();
            }
        MenuOpener();
        }


    private void openBranchAttendee(int answer){
        if (answer == 1){
            MessengerOpener();
        }else if(answer == 2){
            List<String> EventList = cs.viewEvents();
            StringPrinter(EventList);
        }else if(answer == 3){
            List<String> events = cs.viewSignedUpOrOrganizedEvents();
            StringPrinter(events);
        }else if(answer ==4){
            List<String> events = cs.viewCanSignUpEvents();
            StringPrinter(events);
        }else if(answer == 5){
            SignUpEvent();
        }else if(answer == 6){
            cancelEnrollment();
        }else{
            model.invalidInput();
        }
        MenuOpener();
    }


    public void MenuOpener() {
        if (type == 0) {
            int answer = model.SpeakerMenu();
            openBranchSpeaker(answer);

        } else if (type == 1) {
            int answer = model.OrganizerMenu();
            openBranchOrganizer(answer);

        } else {
            int answer = model.AttendeeMenu();
            openBranchAttendee(answer);
        }
    }


    private void MessengerOpener(){
        int answer = model.Messenger();
        if (answer == 1){
            MessengerOpenerSpecific();
        }else if(answer == 2){
            List<String> receive_messages = cs.readReceivedMessages();
            List<String> sent_messages= cs.readSentMessages();
            System.out.println("Here are the messages you received!");
            StringPrinter(receive_messages);
            System.out.println("Here are the messages you sent");
            StringPrinter(sent_messages);

        }else if (answer == 3){
            if(type == 1){
                System.out.println("Sorry! You can't reply to a message, since no one can send a message to you!");
            }
            replyMessage();
        }else{
            MenuOpener();
        }
    }

    private void SenderSpeaker(int answer){
        if (answer == 1){
            messageAllAttendeeEvent();
        }else if (answer == 2){
            messageAllUsersInAllSpeakingEvents();
        }else if(answer == 3){
            messageOneSpecificUserInEvent();
        }else {
            MessengerOpener();
        }
    }

    private void SenderOrganizer(int answer){
        if(answer ==1){
            messageAttendee();
        }else if (answer == 2){
            messageSpeaker();
        }else if (answer == 3){
            messageAllAttendee();
        }else if (answer == 4){
            messageAllSpeakers();
        }else{
            MessengerOpener();
        }
    }

    private void SenderAttendee(int answer){
        if(answer ==1){
            messageAttendee();
        }else if (answer == 2){
            messageSpeaker();
        }else{
            MessengerOpener();
        }
    }



    private void MessengerOpenerSpecific(){
        if (type == 0){
           int answer = model.MessengerSpeaker();
           SenderSpeaker(answer);
        }else if (type == 1){
            int answer = model.MessengerOrganizer();
            SenderOrganizer(answer);
        }else{
            int answer = model.MessengerAttendee();
            SenderAttendee(answer);
        }
    }




    private void SignUpEvent(){

        String eventID = model.SignUpEvent();
        boolean success = cs.signUpForEvent(eventID);
        if (success){
            System.out.println("Hey! You just sign up a new event! Arrive on time! ");
        }else{
            System.out.println("Sorry! You can't sign up for the event. Please check again!");
            MenuOpener();
        }
    }




    private void addRoom(){
        String room = model.addRoom();
        boolean success = cs.addNewRoom(room);
        if (success){
            System.out.println("Hey! You just make a new room");
        }else{
            System.out.println("Sorry! We can not add the room. Please check again!");
            MenuOpener();
        }
    }


    private void addSpeaker(){
        String[] l = model.speakerAdder();
        String speaker_name = l[0];
        String speaker_pass = l[1];
        boolean success = cs.addNewSpeaker(speaker_name, speaker_pass);
        if (success){
            System.out.println("Hey! You just make a new speaker account!");
        }else{
            System.out.println("Sorry! We can not add the speaker. Please check again!");
            MenuOpener();
        }
    }
    private void setSpeakerEvent(){
        String[] l = model.setSpeakerEvent();
        String speaker_id = l[0];
        String event_id = l[1];
        boolean success = cs.setSpeakerForEvent(speaker_id, event_id);
        if (success){
            System.out.println("Hey! You just make set a speaker for event!");
        }else{
            System.out.println("Sorry! We can not set the speaker for the event. Please check again!");
            MenuOpener();
        }
    }

    private void cancelEnrollment(){
        String event_id = model.cancelEnrollment();
        boolean success = cs.cancelEnrollmentInEvent(event_id);
        if (success){
            System.out.println("You just cancel an amazing event!");
        }else{
            System.out.println("Oops! You cannot cancel that event! Please check again!");
            MenuOpener();
        }
    }

    private void MessageSuccessHelper(boolean success){
        if (success){
            System.out.println("Message is sent successfully");
        }else{
            System.out.println("Oops! You cannot send that message! Please check again!");
            MessengerOpener();
        }
    }

    private void replyMessage(){
        List<String> receive_message = cs.readReceivedMessages();
        String[] l = model.replyMessage(receive_message);
        String num = l[0];
        String reply = l[1];
        boolean success = cs.replyMessage(num, reply);
        MessageSuccessHelper(success);

    }

    private void messageAllAttendeeEvent() {
        String[] l = model.MessageAllAttendeeEvent();
        String event_id = l[0];
        String message = l[1];
        boolean success = cs.messageAllAttendeesInEvent(event_id, message);
        MessageSuccessHelper(success);
    }

    private void messageAllUsersInAllSpeakingEvents(){
        String message = model.messageAllUsersInAllSpeakingEvents();
        boolean success = cs.messageAllUsersInAllSpeakingEvents(message);
        MessageSuccessHelper(success);

    }

    private void messageOneSpecificUserInEvent(){
        String[] l = model.messageOneSpecificUserInEvent();
        String event_id = l[0];
        String user_id = l[1];
        String message = l[2];
        boolean success = cs.messageOneSpecificUserInEvent(event_id, user_id, message);
        MessageSuccessHelper(success);
    }

    private void messageAllSpeakers(){
        String message = model.messageAllSpeakers();
        boolean success = cs.messageAllSpeakers(message);
        MessageSuccessHelper(success);
    }

    private void messageSpeaker(){
        String[] l = model.messageSpeaker();
        String user_id = l[0];
        String message = l[1];
        boolean success = cs.messageSpeaker(user_id, message);
        MessageSuccessHelper(success);
    }

    private void messageAllAttendee(){
        String message = model.messageAllAttendee();
        boolean success = cs.messageAllAttendee(message);
        MessageSuccessHelper(success);
    }

    private void messageAttendee(){
        String[] send = model.messageAttendee();
        String id_receive = send[0];
        String content = send[1];
        boolean success  = cs.messageAttendee(id_receive, content);
        MessageSuccessHelper(success);
    }

    private void new_event(){
        String[] l = model.newEvent();
        String start = l[0];
        String user_id = l[1];
        String topic = l[2];
        String room = l[3];
        boolean success = cs.newEvent(start, user_id, topic, room);
        MessageSuccessHelper(success);

    }




    }





