package controller;

import gateway.Gateway;
import usecase.EventManager;
import usecase.MessageManager;
import usecase.UserManager;

public class OrganizerSystem {

    private UserManager um = new UserManager();
    private Gateway gw = new Gateway();

    public boolean CanCreateUser(int id, String userName, String password){
        if (password.trim().length() >=6 && userName.trim().length() > 0 && um.canCreateUser(userName, gw)
                && um.isExistingOrganizer(id, gw)){
            return true;
        }
        return false;
    }

    public boolean CreateSpeaker(String userName, String password){
        if (um.canCreateSpeaker(userName, gw)){
            um.createSpeaker(password, userName, gw);
            return true;
        }
        return false;
    }

    public boolean CreateAttendee(String userName, String password){
        if(um.canCreateAttendee(userName, gw)){
            um.createAttendee(password, userName, gw);
            return true;
        }
        return false;
    }
}
