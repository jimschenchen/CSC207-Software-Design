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

    private void Introduction(){
        model.Introduction();
    }

    private void CredentialsHelper(){
        String username = model.username();
        String pass = model.Pass();
    }

    private void IsUserRegistered(){
        if (model.AreURegistered() == 1){
            CredentialsHelper();
        }
        else{
            model.SignIn();
        }
    }
    private void EverythingCorrect(){
        type = cs.login(id, (String)pass);
        if (type != -1){
            model.Credentials(username);
        }
        else{
            model.CredentialsFail();
            CredentialsHelper();
        }
    }
    private void MenuOpener(){
        if (type == 0){
            model.SpeakerMenu();
        }else if (type == 1){
            model.OrganizerMenu();
        }else{
            model.AttendeeMenu();
        }


    }
    private void MessengerOpener(){
        if (type == 0){
            model.MessengerSpeaker();
        }else if (type == 1){
            model.MessengerOrganizer();
        }else{
            model.MessengerAttendee();
        }
    }




    private void SignUpEvent(){

        String eventID = model.SignUpEvent();
        boolean success = cs.signUpForEvent(eventID);
        if (success){
            System.out.println("Hey! You just sign up a new event! Arrive on time! ");
        }else{
            System.out.println("Sorry! You can't sign up for the event. Please check again!");
        }
    }




    private void addRoom(){
        String room = model.addRoom();
        boolean success = cs.addNewRoom(room);
        if (success){
            System.out.println("Hey! You just make a new room");
        }else{
            System.out.println("Sorry! We can not add the room. Please check again!");
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
        }
    }

    private void cancelEnrollment(){
        String event_id = model.cancelEnrollment();
        boolean success = cs.cancelEnrollmentInEvent(event_id);
        if (success){
            System.out.println("You just cancel an amazing event!");
        }else{
            System.out.println("Oops! You cannot cancel that event! Please check again!");
        }
    }

    private void MessageSuccessHelper(boolean success){
        if (success){
            System.out.println("Message is sent successfully");
        }else{
            System.out.println("Oops! You cannot send that message! Please check again!");
        }
    }

    private void replyMessage(){
        String[] l = model.replyMessage();
        String receiver_id = l[0];
        String reply = l[1];
        boolean success = cs.replyMessage(receiver_id, reply);
        MessageSuccessHelper(success);

    }

    private void messageAllAttendeeEvent() {
        String[] l = model.MessageAllAttendeeEvent();
        String event_id = l[0];
        String message = l[1];
        boolean success = cs.messageAllAttendeesInEvent(event_id, message);
        MessageSuccessHelper(success) ;
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

    private void messageAttendee(){//for specific one
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
    }



    }





