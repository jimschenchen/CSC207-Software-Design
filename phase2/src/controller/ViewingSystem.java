package controller;

import gateway.GatewayFacade;
import usecase.EventManager;
import usecase.RoomManager;
import usecase.UserManager;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class ViewingSystem {

    /**
     * Viewing System which allows users to view users, events and rooms in the Conference.
     */

    private EventManager em = new EventManager();
    private UserManager um = new UserManager();
    private RoomManager rm = new RoomManager();
    private int user;

    void setUser(int userID){
        this.user = userID;
    }

    private List<String> getEventList(List<Integer> idList, GatewayFacade gw){
        List<String> sEvents = new ArrayList<>();
        for (Integer id : idList){
            sEvents.add(em.getStringOfEvent(id, gw));
        }
        return sEvents;
    }

    /**
     * Return all events that are currently scheduled.
     *
     * @return List of Strings of the events
     */
    List<String> viewEvents(GatewayFacade gw){
        List<Integer> events = em.getEventList(gw);
        return getEventList(events, gw);
    }

    /**
     * Return a list of events that the current logged in user has signed up for.
     *
     * @return List of Strings of the events
     */
    List<String> viewSignedUpEvents(GatewayFacade gw){
        List<Integer> events = um.getOrganizerOrAttendeeEventList(user, gw);
        return getEventList(events, gw);
    }

    /**
     * Return a list of events that the current logged in organizer has organized
     *
     * @return List of Strings of the events
     */
    List<String> viewOrganizedEvents(GatewayFacade gw){
        List<Integer> events = um.getOrganizedEventList(user, gw);
        return getEventList(events, gw);
    }

    /**
     * Return a list of events that the current Speaker is going to speak for.
     *
     * @return List of Strings of the events
     */
    List<String> viewSpeakingEvents(GatewayFacade gw){
        List<Integer> events = um.getSpeakerEventList(user, gw);
        return getEventList(events, gw);
    }

    /**
     * Return a list of events that the current logged in attendee can sign up for.
     *
     * @return List of Strings of the events
     */
    List<String> viewCanSignUpEvents(GatewayFacade gw){
        List<Integer> allEvents = em.getEventList(gw);
        List<String> events = new ArrayList<>();
        for (Integer eventID : allEvents){
            if (um.canSignUpForEvent(eventID, this.user, gw) && em.canAddUserToEvent(user, eventID, gw)){
                events.add(em.getStringOfEvent(eventID, gw));
            }
        }
        return events;
    }

    /**
     * View all attendees who are registered in one of the current speaker's speaking events. Without duplicates.
     *
     * @return Return a list of Strings that represent all attendees of all the events the speaker is speaking for.
     * Every attendee is represented by a string formated as follows: "UserName (userID)"
     */
    List<String> viewAttendeesInSpeakingEvents(GatewayFacade gw){
        List<Integer> allSpeakingEvents = um.getSpeakerEventList(user, gw);
        Set<Integer> allAttendeesInEvents = new LinkedHashSet<>();
        List<String> sAllAttendeesInEvents = new ArrayList<>();
        for (Integer eventID : allSpeakingEvents){
            List<Integer> usersInEvent = em.getUserList(eventID, gw);
            allAttendeesInEvents.addAll(usersInEvent);
        }
        for (Integer userID : allAttendeesInEvents){
            sAllAttendeesInEvents.add(um.getUserString(userID, gw));
        }
        return sAllAttendeesInEvents;
    }

    private List<String> getUserList(List<Integer> idList, GatewayFacade gw){
        List<String> sUser = new ArrayList<>();
        for (Integer id : idList){
            sUser.add(um.getUserString(id, gw));
        }
        return sUser;
    }

    /**
     * View all attendees in the system.
     *
     * @return Return a list of Strings that represent all attendees in the system.
     * Every attendee is represented by a string formatted as follows: "UserName (userID)"
     */
    List<String> viewAllAttendees(GatewayFacade gw){
        List<Integer> allAttendees = um.getListOfUsers(2, gw);
        return getUserList(allAttendees, gw);
    }

    /**
     * View all speakers in the system.
     *
     * @return Return a list of Strings that represent all speakers in the system.
     * Every speaker is represented by a string formatted as follows: "UserName (userID)"
     */
    List<String> viewAllSpeakers(GatewayFacade gw){
        List<Integer> allSpeakers = um.getListOfUsers(0, gw);
        return getUserList(allSpeakers, gw);
    }

    List<String> viewAllRooms(GatewayFacade gw){
        /**
         * View all rooms in the system.
         *
         * @return Return a list of strings that represent all rooms in the system.
         * Every room is represented by a string formatted as follows: "RoomName/Number (RoomID)"
         */
        return rm.AllRooms(gw);
    }
}
