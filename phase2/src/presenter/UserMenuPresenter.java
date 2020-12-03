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
            userMenu.update();
        }
    }
}
