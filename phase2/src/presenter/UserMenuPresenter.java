package presenter;

import controller.ConferenceSystem;

import java.util.List;

class UserMenuPresenter {
    ConferenceSystem cs = new ConferenceSystem();
    IMessage userMenu = new UserMenu();


    void menuItemClicked(int i){
        if (i == 1){
            IMessage messenger = new MessengerWindow();
        }

    }

    void updatePassword(String password){
        userMenu.messageSuccess(cs.resetPassword(password));
    }

    List<List<String>> viewEvents(){
        return cs.viewEvents();
    }

    List<List<String>> viewSignedUpEvents(){
        return cs.viewSignedUpEvents();
    }

    void sgnUpEvent(String eventID){
        boolean success = cs.signUpForEvent(eventID);
        userMenu.messageSuccess(success);
    }

    void cancelEvent(String eventID){
        boolean success = cs.cancelEvent(eventID);
        userMenu.messageSuccess(success);
    }
}
