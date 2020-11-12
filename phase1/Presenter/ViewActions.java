
public interface ViewActions {
    void Introduction();
    int AreURegistered();
    String Username();
    String[] SignIn();
    String Pass();
    void Credentials(String username);
    void CredentialsFail();
    int Messenger();
    int MessengerOrganizer();
    int MessengerSpeaker();
    String[] SenderView(String message);
    String UserAdder(String username);
    int AttendeeMenu();
    int OrganizerMenu();
    int SpeakerMenu();
}
