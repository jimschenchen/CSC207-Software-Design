import java.io.Console;
import java.sql.SQLOutput;
import java.util.List;

public class ViewModel implements ViewActions {
    ConferenceSystem cs = new ConferenceSystem();

    @Override
    public void Introduction() {
        System.out.println("Welcome to the 0173 Conference!");
    }

    @Override
    public int AreURegistered() {
        int i = - 1;
        Console c = System.console();
        System.out.println("Do you have an account? Type: Yes/No");
        String n = c.readLine();
        if (n.equals("Yes")) {
            i = 1;
        } else if (n.equals("No")) {
            i = 0;
        }else{
            System.out.println("Invalid input! Please Type; Yes/No");
        }
        return i;
    }

    @Override
    public String username() {
        Console c = System.console();
        System.out.println("Enter your username here:");
        String username = c.readLine();
        return username;
    }


    @Override
    public String Pass() {
        Console c=System.console();
        System.out.println("Enter your password here: ");
        char[] ch=c.readPassword();
        String pass = String.valueOf(ch);//converting char array into string
        return pass;
    }

    @Override
    public String[] SignIn(){
        Console c = System.console();
        System.out.println("To sign in enter your username here:");
        String n = c.readLine();
        System.out.println("To sign in enter your password here:");
        String k = c.readLine();
        cs.login(n,k); // lust to save the information in cs
        return new String[]{n, k};
    }

    @Override
    public void Credentials(String username) {
        System.out.println("Welcome" + username + "!");
    }
    @Override
    public void CredentialsFail(){
        System.out.println("Oops, something went wrong. Please try again");
    }

    public int Messenger(){
        System.out.println("Welcome to Messenger!");
        System.out.println("Type 1 If you want to send a message: ");
        System.out.println("Type 2 If you want to view your messages: ");;
        return answer();
    }

    private int answer(){
        int i = 0;
        Console c = System.console();
        String answer = c.readLine();
        i = Integer.parseInt(answer);
        return i;
    }



    @Override
    public int MessengerAttendee() {
        System.out.println("Type 1 If you want to send a message to an attendee: ");
        System.out.println("TYpe 2 If you want to send a message to a speaker: ");
        return answer();

    }

    @Override
    public int MessengerOrganizer() {
        System.out.println("Type 1 If you want to send a message to an attendee: ");
        System.out.println("TYpe 2 If you want to send a message to a speaker: ");
        System.out.println("Type 3 If you want to send a message to all attendees: ");
        System.out.println("TYpe 4 If you want to send a message to all speakers: ");
        return answer();
    }

    @Override
    public int MessengerSpeaker() {
        System.out.println("Type 1 If you want to send a message to all attendees in one event'");
        System.out.println("TYpe 2 If you want to send a message to all attendees in all your events");
        return answer();
    }



    @Override
    public int AttendeeMenu() {
        System.out.println("Welcome to the menu!");
        System.out.println("Type 1 if you want to open the Messenger: ");
        System.out.println("Type 2 if you want to browse all the events in the conference: ");
        System.out.println("Type 3 if you want to see your schedule: ");
        System.out.println("Type 4 if you want to see what events you can sign up now: ");
        System.out.println("Type 5 if you want to sign up a new event: ");
        System.out.println("Type 6 if you want to cancel an event: ");
        return answer();
    }

    @Override
    public int OrganizerMenu() {
        System.out.println("Welcome to the menu!");
        System.out.println("Type 1 if you want to open the Messenger: ");
        System.out.println("Type 2 if you want to create a new room: ");
        System.out.println("Type 3 if you want to create a new speaker: ");
        System.out.println("Type 4 if ou want to assign a speaker to an event");
        return answer();
    }

    @Override
    public int SpeakerMenu() {
        System.out.println("Welcome to the menu!");
        System.out.println("Type 1 if you want to open the Messenger: ");
        System.out.println("Type 2 if you want to see a list of talks you are given: ");
        return answer();
    }

    private void eventsPrinter(List<String> events){
        for (String e: events){
            System.out.println(e);
        }
    }

    public void ViewEvents(){
        List<String> EventList = cs.viewEvents();
        eventsPrinter(EventList);
    }


    public void viewSignedUpOrOrganizedEvents(){
        List<String> events = cs.viewSignedUpOrOrganizedEvents();
        eventsPrinter(events);

    }

    public void viewCanSignUpEvents(){
        List<String> events = cs.viewCanSignUpEvents();
        eventsPrinter(events);
    }


    public String SignUpEvent(){
        Console c=System.console();
        System.out.println("Enter the event id you would like to attend");
        String eventID = c.readLine();
        return eventID;
    }


    public String addRoom(){
        Console c = System.console();
        System.out.println("Enter the room name you want to add");
        String room = c.readLine();
        return room;
    }

    public String[]  speakerAdder(){
        Console c=System.console();
        System.out.println("Enter the username of the speaker you want to add: ");
        String speaker_name = c.readLine();
        System.out.println("Enter the password of the speaker you want to add: ");
        String speaker_pass = c.readLine();
        String[] l = new String[]{speaker_name, speaker_pass};
        return l;
    }

    public String[] setSpeakerEvent(){
        Console c = System.console();
        System.out.println("Enter the ID of the speaker you want to set for an event: ");
        String speaker_id = c.readLine();
        System.out.println("Enter the event ID you want to set the speaker for: ");
        String event_id = c.readLine();
        String[] l = {speaker_id, event_id};
        return l;
    }
    public String cancelEnrollment(){
        Console c = System.console();
        System.out.println("Enter the ID of the event you want to cancel: ");
        String event_id = c.readLine();
        return event_id;
    }

    public void readMessage(){
        List<String> l = cs.readMessages();
        for (String m : l){
            System.out.println(m);
        }
    }

    private String AskForUser(){
        Console c = System.console();
        System.out.println("What is the ID of the User you want to send message to?");
        String user_id = c.readLine();
        return user_id;
    }

    private String AskForEvent(){
        Console c = System.console();
        System.out.println("What is the ID of the event the user you want to message to is in?");
        String event_id = c.readLine();
        return event_id;
    }

    private String AskForMessage(){
        Console c = System.console();
        System.out.println("Type what you want to send there");
        String message = c.readLine();
        return message;
    }

    public String[] replyMessage(){

        Console c = System.console();
        System.out.println("Enter the ID of whom you want to reply here: ");
        String receiver_id = c.readLine();
        System.out.println("Enter what you want to reply here: ");
        String reply = c.readLine();
        String[] l = {receiver_id, reply};
        return l;
    }

    public String[] MessageAllAttendeeEvent(){
        String event_id = AskForEvent();
        String message = AskForMessage();
        String[] l = {event_id, message};
        return l;
    }

    public String messageAllUsersInAllSpeakingEvents(){
        String message = AskForMessage();
        return message;
    }

    public String[] messageOneSpecificUserInEvent(){
        String event_id = AskForEvent();
        String user_id = AskForUser();
        String message = AskForMessage();
        String[] l = {event_id, user_id, message};
        return l;
    }

    public String messageAllSpeakers(){
        String message = AskForMessage();
        return message;
    }

    public String[] messageSpeaker(){
        String user_id = AskForUser();
        String message = AskForMessage();
        String[] l = {user_id,message};
        return l;
    }

    public String messageAllAttendee(){
        String message = AskForMessage();
        return message;
    }


    public String[] messageAttendee() {
        String n = AskForUser();
        String k = AskForMessage();
        String[] l = new String[]{n, k};
        return l;
    }

    private String AskStartTime(){
        Console c = System.console();
        System.out.println("When does the event start at?");
        String start = c.readLine();
        return start;
    }

    private String AskTopic(){
        Console c = System.console();
        System.out.println("What is the topic of the event");
        String topic = c.readLine();
        return topic;
    }

    public String[] newEvent(){
        String start = AskStartTime();
        String user_id = AskForUser();
        String topic = AskTopic();
        Console c = System.console();
        System.out.println("What is the room number where the event takes place?");
        String room = c.readLine();
        String[] l = {start, user_id, topic, room};
        return l;
    }

}
