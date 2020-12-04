package controller;

import gateway.GatewayFacade;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class EventManagementSystem extends subSystem{

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

    /**
     * Set the speaker of an event.
     *
     * @param speakerID New Speaker's ID
     * @param eventID The event's ID
     * @return Return True when the speaker is assigned to the event successfully, false otherwise.
     */
    boolean changeSpeakerForOneSpeakerEvent(String speakerID, String eventID, GatewayFacade gw){
        try{
            int sID = Integer.parseInt(speakerID);
            int eID = Integer.parseInt(eventID);
            if(um.canAddEventToSpeaker(eID, sID, gw) & gw.getOneSpeakerEventById(eID) != null){
                um.addEventToSpeaker(eID, sID, gw);
                em.setSpeakerToOneSpeakerEvent(sID, eID, gw);
                return true;
            }
            return false; // return false when cannot add event to speaker
        }
        catch(NumberFormatException nfe){
            return false; // return false on invalid input
        }
    }

    boolean addSpeakerToMultiSpeakerEvent(String speakerId, String eventId, GatewayFacade gw) {
        try{
            int sID = Integer.parseInt(speakerId);
            int eID = Integer.parseInt(eventId);
            if(um.canAddEventToSpeaker(eID, sID, gw) & gw.getMultiSpeakerEventById(eID) != null){
                um.addEventToSpeaker(eID, sID, gw);
                em.addSpeakerToMultiSpeakerEvent(sID, eID, gw);
                return true;
            }
            return false; // return false when cannot add event to speaker
        }
        catch(NumberFormatException nfe){
            return false; // return false on invalid input
        }
    }

    // if no speaker, pass in empty string
    // if more than one speaker, pass "id1,id2"
    boolean newEvent(int type, String startTime, String endTime, String speakerID,
                            String topic, String roomNumber, String capacity, GatewayFacade gw){
        try{
            List<Integer> types = determineEventTypes(type);
            LocalDateTime sTime = LocalDateTime.parse(startTime, em.getTimeFormatter());
            LocalDateTime eTime = LocalDateTime.parse(endTime, em.getTimeFormatter());
            int rID = rm.getRoomIDbyRoomNumber(roomNumber, gw);
            int cap = Integer.parseInt(capacity);
            if (!(speakerID.length() == 0)){
                if (speakerID.contains(",")) { // have more than 1 speaker
                    ArrayList<Integer> sID = new ArrayList<>();
                    StringTokenizer token = new StringTokenizer(speakerID,",");
                    while (token.hasMoreElements()){
                        sID.add(Integer.parseInt(token.nextToken()));
                    }
                    return newEventMoreThan1Speaker(types.get(0), types.get(1), sID, sTime, eTime, topic, rID, cap, gw);
                }
                else{ // only have 1 speaker
                    int sID = Integer.parseInt(speakerID);
                    return newEvent1Speaker(types.get(0), types.get(1), sID, sTime, eTime, topic, rID, cap, gw);
                }
            }
            else{ // no speaker
                return newEventNoSpeaker(types.get(0), types.get(1), sTime, eTime, topic, rID, cap, gw);
            }
        }
        catch(NumberFormatException | DateTimeParseException e){
            return false;
        }
    }

    // 0: party, 1: talk, 2: panel discussion
    private List<Integer> determineEventTypes(int pType){
        List<Integer> types = new ArrayList<>();
        switch (pType){
            case 0: types.add(0);
                types.add(0);
                break;
            case 1: types.add(1);
                types.add(0);
                break;
            case 2: types.add(2);
                types.add(0);
                break;
        }
        return types;
    }

    private boolean canCreateEvent(int sID, int rID, LocalDateTime sTime, LocalDateTime eTime, int cap,
                                   GatewayFacade gw){
        return um.isExistingSpeaker(sID, gw) && em.canCreateEvent(rID, sTime, eTime, cap, gw) &&
                um.isSpeakerBusy(sID, sTime, eTime, gw);
    }

    private boolean newEvent1Speaker(int type1, int type2, int sID, LocalDateTime sTime,
                                     LocalDateTime eTime, String topic, int rID, int cap, GatewayFacade gw){
        if (type1 != 1){
            return false;
        }
        if (canCreateEvent(sID, rID, sTime, eTime, cap, gw)) {
            int eventID = em.createEvent(type1, type2, sID, sTime, eTime, topic, rID, cap, gw);
            um.addEventToOrganizedList(eventID, user, gw);
            um.addEventToSpeaker(eventID, sID, gw);
            return true;
        }
        return false;
    }

    private boolean newEventMoreThan1Speaker(int type1, int type2, ArrayList<Integer> sID, LocalDateTime sTime,
                                             LocalDateTime eTIme, String topic, int rID, int cap, GatewayFacade gw){
        if (type1 != 2){
            return false;
        }
        for (int speakerID : sID){
            if (!canCreateEvent(speakerID, rID, sTime, eTIme, cap, gw)){
                return false;
            }
        }
        int eventID = em.createEvent(type1, type2, sID, sTime, eTIme, topic, rID, cap, gw);
        um.addEventToOrganizedList(eventID, user, gw);
        for (int speakerID : sID){
            um.addEventToSpeaker(eventID, speakerID, gw);
        }
        return true;
    }

    private boolean newEventNoSpeaker(int type1, int type2, LocalDateTime sTime, LocalDateTime eTime,
                                      String topic, int rID, int cap, GatewayFacade gw){
        if (type1 != 0 || !em.canCreateEvent(rID, sTime, eTime, cap, gw)){
            return false;
        }
        int eventID = em.createEvent(type1, type2, sTime, eTime, topic, rID, cap, gw);
        um.addEventToOrganizedList(eventID, user, gw);
        return true;
    }

    boolean cancelEvent(String eventID, GatewayFacade gw){
        try{
            int eID = Integer.parseInt(eventID);
            if (um.canCancelEvent(user, eID, gw) && em.canCancelEvent(eID, gw)){
                um.cancelEvent(eID, gw);
                em.cancelEvent(eID, gw);
                return true;
            }
            return false;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    boolean changeEventCapacity(String eventId, String capacity, GatewayFacade gw) {
        try {
            int cap = Integer.parseInt(capacity);
            int eid = Integer.parseInt(eventId);
            if (em.canChangeEventCapacity(eid, cap, gw) && um.canChangeEventCapacity(user, gw)) {
                em.changeEventCapacity(eid, cap, gw);
                return true;
            }
            return false;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
