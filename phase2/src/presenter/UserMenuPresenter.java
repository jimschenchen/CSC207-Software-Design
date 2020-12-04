package presenter;

import controller.ConferenceSystem;

public class UserMenuPresenter extends Presenter{
    private ConferenceSystem cs = new ConferenceSystem();
    private IWindow userMenu;

    public UserMenuPresenter(IWindow userMenu){
        this.userMenu = userMenu;
    }

    public void menuItemClicked(int i){
        if (i == 4){
            String type = "passwordField";
            userMenu.update(type);
            cs.resetPassword();
        }
        if (i == 5){

        }
        if (i == 6){

        }
        if (i == 7){

        }
        if (i == 8){

        }
        if (i == 9){

        }
        if (i == 10){

        }
        if (i == 11){

        }
        if (i == 12){

        }
        if (i == 13){

        }
        if (i == 14){

        }


    }
}
