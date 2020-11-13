package Presenter;

import java.io.Console;
public class ViewModel implements ViewActions {
    @Override
    public void Introduction() {
        System.out.println("Welcome to the 0173 Conference!");
    }

    @Override
    public int AreURegistered() {
        int i = 0;
        Console c = System.console();
        System.out.println("Do you have an account? Type: Yes/No");
        String n = c.readLine();
        if (n.equals("Yes")) {
            i = 1;
        } else if (n.equals("No")) {
            i = 0;
        }
        //Should we check if User writes down anything else?
        return i;
    }

    @Override
    public String Username() {
        Console c = System.console();
        System.out.println("Enter your username here:");
        String user = c.readLine();
        return user;
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

    @Override
    public int Messenger() {
        int i = 0;
        Console c=System.console();
        System.out.println("Welcome to Messenger!");
        System.out.println("Type 1 If you want to send the message:");
        System.out.println("Type 2 If you want to view your messages");
        System.out.println("Type 3 If you want to view your FriendList");
        System.out.println("Type 4 If you want to add new user to your Friendlist");
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
        System.out.println("Type 5 If you want to send the message to all the users:");
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }

    @Override
    public int MessengerSpeaker() {
        int i = 0;
        Console c=System.console();
        System.out.println("Type 6 If you want to send the message to all talk's attendees:");
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }

    @Override
    public String[] SenderView(String message) {
        Console c=System.console();
        System.out.println("Type the username you want send message to");
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

    @Override
    public int AttendeeMenu() {
        System.out.println("Welcome to Menu!");
        Console c=System.console();
        int i = 0;
        System.out.println("Type 1 If you want to open messenger: ");
        System.out.println("Type 2 If you want to view the schedule: ");
        System.out.println("Type 3 If you want to sign up to a new event: ");
        System.out.println("Type 4 If you want to cancel the event: ");
        String n = c.readLine();
        i = Integer.parseInt(n);
        return i;
    }

    @Override
    public int OrganizerMenu() {
        return 0;
    }

    @Override
    public int SpeakerMenu() {
        return 0;
    }
    //I didn't implement Organizer and Speaker menu, cause I am not sure how it should work.
    //I.e., should it implement the AttendeeMenu method?
    //Maybe we can separate the console so that we wouldn't have to type the same part of the code all the time

    //reply for each type of user, menu should be different and restrict them to use method. e.g. speaker menu won't let the speaker use sign up an event method...
}
