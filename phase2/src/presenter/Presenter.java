package presenter;

import controller.ConferenceSystem;

public class Presenter {
    private int type;
    ConferenceSystem cs = new ConferenceSystem();
    public void signIn( String username, String password){
        type = cs.login(username, password);

    }

    public void menuItemClicked(String string){

    }
}
