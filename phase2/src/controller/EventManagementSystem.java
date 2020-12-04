package controller;

import entity.Room;
import gateway.GatewayFacade;
import usecase.EventManager;
import usecase.RoomManager;
import usecase.UserManager;

class EventManagementSystem {

    private int user;
    private EventManager em = new EventManager();
    private UserManager um = new UserManager();
    private RoomManager rm = new RoomManager();

    void setUser(int userID){
        this.user = userID;
    }

    boolean changeVipStatusOfEvent(int eventId, Boolean type, GatewayFacade gw){
        /**
         * change type of a event
         * @param eventId eventid of event
         * @return Return true if change correctly, false otherwise.
         */
        if (em.getVipStatusOfEvent(eventId, gw) != type && um.isExistingOrganizer(user, gw)){
            return em.changeVipStatusOfEvent(eventId, type, gw);
        }
        return false;
    }

    boolean getVipStatusOfEvent(int eventId, GatewayFacade gw){
        /**
         * return the event type, true means event is VIP, false means event is not VIP
         * @param eventID event id
         * @return the type of event
         */
        return em.getVipStatusOfEvent(eventId, gw);
    }
}
