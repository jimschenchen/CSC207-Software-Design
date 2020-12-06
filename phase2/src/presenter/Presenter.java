package presenter;

import controller.ConferenceSystem;

public class Presenter {
    private int type;
    ConferenceSystem cs;

    public Presenter(){
        this.cs = new ConferenceSystem();
    }

    public void signIn( String username, String password, IUpdate window){
        type = cs.login(username, password);
        if(type == 0){
            window.update("Speaker");
        }else if(type == 1){
            window.update("Organizer");
        }else if(type == 2){
            window.update("Attendee");
        }
    }

    public void signup(String username, String password, IUpdate window){
        Boolean bool = cs.signup(username, password);
        if (bool){
            window.update("createAccount");
        }else{
            window.update("error");
        }
    }






}
