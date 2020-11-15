public interface ViewActions {
    void Introduction();
    int AreURegistered();
    String username();
    String[] SignIn();
    String Pass();
    void Credentials(String username);
    void CredentialsFail();
    int MessengerAttendee();
    int MessengerOrganizer();
    int MessengerSpeaker();
    String[] SenderView();
    String UserAdder(String username);
    int AttendeeMenu();
    int OrganizerMenu();
    int SpeakerMenu();
}
