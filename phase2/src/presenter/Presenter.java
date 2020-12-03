package presenter;

import controller.ConferenceSystem;

public class Presenter {
    private int type;
    ConferenceSystem cs = new ConferenceSystem();

    public int signIn( String username, String password){
        type = cs.login(username, password);
        return type;
    }

    public Boolean signup(String username, String password){
        Boolean bool = cs.signup(username, password);
        return bool;
    }

    public void menuItemClicked(String string){

    }


}
