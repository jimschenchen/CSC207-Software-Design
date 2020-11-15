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
        /**
         * The construction for presenter.
         *
         * @Param model a model of the class ViewModel.
         * @return: void
         * @Date: 2020-11-15
         */
        this.model = model;
    }

    public void Introduction(){
        /**
         * Welcome users to the conference.
         *
         * @Param []
         * @return: void
         * @Date: 2020-11-15
         */
        model.Introduction();
    }

    private void SignUp(){
        String username = model.username();
        String pass = model.Pass();
        boolean success = cs.signup(username, pass);
        if (!success) {
            model.MessageErr();
        }
        IsUserRegistered();
    }

    public void IsUserRegistered(){
        /**
         * Asks if the user is registered
         *
         * @Param []
         * @return: void
         * @Date: 2020-11-15
         */
        if (model.AreURegistered() == 0){
            SignUp();
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

    private void EverythingCorrect(){
        type = cs.login(id, pass);
        if (type != -1){
            model.Credentials(username);
            MenuOpener();
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
            viewSpeakingEvents();
        }else {
            model.invalidInput();
            MenuOpener();
        }
    }




    private void openBranchOrganizer(int answer){
        if (answer == 1){
            MessengerOpener();
        }else if(answer == 2){
            addRoom();
            MenuOpener();
        }else if(answer == 3){
            addSpeaker();
            MenuOpener();
        }else if (answer == 4){
            setSpeakerEvent();
            MenuOpener();
        }else if(answer == 5){
            new_event();
            MenuOpener();
        }else{
            model.invalidInput();
            MenuOpener();
        }
    }


    private void openBranchAttendee(int answer){
        if (answer == 1){
            MessengerOpener();
        }else if(answer == 2){
            viewEvents();
        }else if(answer == 3){
            viewSignedUpOrOrganizedEvents();
        }else if(answer ==4){
            viewCanSignUpEvents();
        }else if(answer == 5){
            SignUpEvent();
            MenuOpener();
        }else if(answer == 6){
            cancelEnrollment();
            MenuOpener();
        }else{
            model.invalidInput();
            MenuOpener();
        }
    }


    private void viewCanSignUpEvents(){
        List<String> events = cs.viewCanSignUpEvents();
        StringPrinter(events);
        String answer = model.back();
        if (answer != null){
            MenuOpener();
        }else{
            viewCanSignUpEvents();
        }
    }

    private void viewSpeakingEvents(){
        List<String> l = cs.viewSpeakingEvents();
        StringPrinter(l);
        String answer = model.back();
        if (answer != null){
            MenuOpener();
        }else{
            viewSpeakingEvents();
        }

    }

    private void viewSignedUpOrOrganizedEvents(){
        List<String> events = cs.viewSignedUpOrOrganizedEvents();
        StringPrinter(events);
        String answer = model.back();
        if (answer != null){
            MenuOpener();
        }else{
            viewCanSignUpEvents();
        }
    }

    private void viewEvents(){
        List<String> EventList = cs.viewEvents();
        StringPrinter(EventList);
        String answer = model.back();
        if (answer != null){
            MenuOpener();
        }else{
            viewEvents();
        }
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



    private void replyMessage(){
        List<String> receive_message = cs.readReceivedMessages();
        String[] l = model.replyMessage(receive_message);
        String num = l[0];
        String reply = l[1];
        boolean success = cs.replyMessage(num, reply);
        MessageSuccessHelper(success);

    }

    private void viewMessage(){
        List<String> receive_messages = cs.readReceivedMessages();
        List<String> sent_messages= cs.readSentMessages();
        System.out.println("Here are the messages you received!");
        StringPrinter(receive_messages);
        System.out.println("Here are the messages you sent");
        StringPrinter(sent_messages);
        String answer1 = model.back();
        if (answer1 != null){
            MessengerOpener();
        }else{
            viewMessage();
        }
    }


    private void MessengerOpener(){
        int answer = model.Messenger();
        if (answer == 1){
            MessengerOpenerSpecific();
        }else if(answer == 2){
            viewMessage();
        }else if (answer == 3){
            if(type == 1){
                model.SorryMessenger();
            }
            replyMessage();
        }else{
            MenuOpener();
        }
    }

    private void SenderSpeaker(int answer){
        if (answer == 1){
            messageAllAttendeeEvent();
            MessengerOpenerSpecific();
        }else if (answer == 2){
            messageAllUsersInAllSpeakingEvents();
            MessengerOpenerSpecific();
        }else if(answer == 3){
            messageOneSpecificUserInEvent();
            MessengerOpenerSpecific();
        }else {
            MessengerOpener();
        }
    }

    private void SenderOrganizer(int answer){
        if(answer ==1){
            messageAttendee();
            MessengerOpenerSpecific();
        }else if (answer == 2){
            messageSpeaker();
            MessengerOpenerSpecific();
        }else if (answer == 3){
            messageAllAttendee();
            MessengerOpenerSpecific();
        }else if (answer == 4){
            messageAllSpeakers();
            MessengerOpenerSpecific();
        }else{
            MessengerOpener();
        }
    }

    private void SenderAttendee(int answer){
        if(answer ==1){
            messageAttendee();
            MessengerOpenerSpecific();
        }else if (answer == 2){
            messageSpeaker();
            MessengerOpenerSpecific();
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
            model.SignUpEventMessage();
        }else{
            model.SignUpEventErr();
        }
    }



    private void addRoom(){
        String room = model.addRoom();
        boolean success = cs.addNewRoom(room);
        if (success){
            model.AddNewRoom();
        }else{
            model.AddNewRoomErr();
        }
    }


    private void addSpeaker(){
        String[] l = model.speakerAdder();
        String speaker_name = l[0];
        String speaker_pass = l[1];
        boolean success = cs.addNewSpeaker(speaker_name, speaker_pass);
        if (success){
            model.SpeakerAccCreated();
        }else{
            model.SpeakerAccCreatedErr();
        }
    }
    private void setSpeakerEvent(){
        String[] l = model.setSpeakerEvent();
        String speaker_id = l[0];
        String event_id = l[1];
        boolean success = cs.setSpeakerForEvent(speaker_id, event_id);
        if (success){
            model.SpeakerSet();
        }else{
            model.SpeakerSetErr();
        }
    }

    private void cancelEnrollment(){
        String event_id = model.cancelEnrollment();
        boolean success = cs.cancelEnrollmentInEvent(event_id);
        if (success){
            model.CancelEventMessage();
        }else{
            model.AddNewRoomErr();
        }
    }

    private void MessageSuccessHelper(boolean success){
        if (success){
            model.MessageSuccess();
        }else{
            model.MessageErr();
            MessengerOpener();
        }
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
