package presenter;

import controller.ConferenceSystem;

public class MyUserMenuPresenter extends Presenter{
    private ConferenceSystem cs = new ConferenceSystem();
    private IUpdate userMenu;

    public MyUserMenuPresenter(IUpdate userMenu){
        this.userMenu = userMenu;
    }

    public void menuItemClicked(int i){
        if (i == 4){
            String type = "passwordField";
            userMenu.update(type);
//            cs.resetPassword();
        }

    }


}
