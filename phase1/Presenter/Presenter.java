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

    private String Sender(){
        String[] send = model.SenderView();
        String id_receive = send[0];
        String content = send[1];
        // Controller needed
    }

    private void SignUpEvent(){

        String eventID = model.SignUpEvent();
        cs.signUpForEvent(eventID);

    }

    private void addRoom(){
        String room = model.addRoom();
        cs.addNewRoom(room); // waiting for grace to change
    }


}

