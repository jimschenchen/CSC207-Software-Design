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
        String id = c.readLine();
        return id;
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
        System.out.println("To sign in enter your id here:");
        String n = c.readLine();
        System.out.println("To sign in enter your password here:");
        String k = c.readLine();
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

    private void MessengerHelper(){
        System.out.println("Welcome to Messenger!");
        System.out.println("Type 1 If you want to send the message:");
        System.out.println("Type 2 If you want to view your messages");
        System.out.println("Type 3 If you want to view your FriendList");
        System.out.println("Type 4 If you want to add new user to your Friendlist");
    }


    @Override
    public int MessengerAttendee() {
        int i = 0;
        Console c=System.console();
        MessengerHelper();
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }
    //We should also have a method that shows all the messages in the console, but I am not sure how it would work
    //The controller is not fully implemented
    @Override
    public int MessengerOrganizer() {
        int i = 0;
        Console c=System.console();
        MessengerHelper();
        System.out.println("Type 5 If you want to send the message to all the users:");
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }

    @Override
    public int MessengerSpeaker() {
        int i = 0;
        Console c=System.console();
        MessengerHelper();
        System.out.println("Type 6 If you want to send the message to all talk's attendees:");
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }

    @Override
    public String[] SenderView() {
        Console c=System.console();
        System.out.println("Type the id you want send message to");
        String n = c.readLine();
        System.out.println("Type your message here:");
        String k = c.readLine();
        String[] l = new String[]{n, k};
        return l;
    }
    @Override
    public String UserAdder(String username) {
        Console c=System.console();
        System.out.println("Type the username you want add");
        String n = c.readLine();
        return n;
    }

    private void MenuHelper(){
        System.out.println("Welcome to Menu!");
        System.out.println("Type 1 If you want to open messenger: ");
        System.out.println("Type 2 If you want to view the schedule: ");
        System.out.println("Type 3 If you want to sign up to a new event: ");
        System.out.println("Type 4 If you want to cancel the event: ");
    }

    @Override
    public int AttendeeMenu() {
        Console c=System.console();
        int i = 0;
        MenuHelper();
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }

    @Override
    public int OrganizerMenu() {
        Console c=System.console();
        int i = 0;
        MenuHelper();
        System.out.println("Type 5 if you want to create a new room: ");
        System.out.println("Type 6 if you want to create a new speaker accounts: ");
        System.out.println("Type 7 if you want to add a new event: ");
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }

    @Override
    public int SpeakerMenu() {
        Console c=System.console();
        int i = 0;
        MenuHelper();
        System.out.println("Type 5 if you want to see your list of talks");
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }
    private void EventViewer(List<Event> EventList) {
        for (Event e : EventList) {
            System.out.println("The Event " + e.getTitle() +
                    " with id " + e.getEvent_id() +
                    " by " + e.getSpeakerId() +
                    " starts at " + e.getStart_time() +
                    " takes place in" + e.getRoomId());
        }
    }

    public void ViewSchedule(){
        ConferenceSystem cs = new ConferenceSystem();
        List<Event> EventList = cs.viewCurrentEvents();
        EventViewer(EventList);
    }

    public String SignUpEvent(){
        Console c=System.console();
        System.out.println("Enter the event id you would like to attend");
        String eventID = c.readLine();
        return eventID;
    }

    public void ViewSignUpEvent(String id){

    }

    public String addRoom(){
        Console c = System.console();
        System.out.println("Enter the room name you want to add");
        String room = c.readLine();
        return room;
    }


}
