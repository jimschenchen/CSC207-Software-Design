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
        }else{
            window.update("error");
        }
    }

    public void signup(String username, String password, IUpdate window){
        Boolean bool = cs.signup(username, password);
        System.out.println(bool);
        if (bool){
            window.update("succeedCreateAccount");
        }else{
            window.update("failCreateAccount");
        }
    }






}
