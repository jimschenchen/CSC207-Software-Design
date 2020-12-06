package presenter;

import controller.ConferenceSystem;

public class Presenter {
    private int type;
    ConferenceSystem cs = new ConferenceSystem();

    public void signIn( String username, String password, IUpdate window){
        type = cs.login(username, password);
        window.update(String.valueOf(type));
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
