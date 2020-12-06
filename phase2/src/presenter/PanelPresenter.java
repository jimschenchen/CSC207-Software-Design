package presenter;

import com.sun.org.apache.xpath.internal.operations.Bool;
import controller.ConferenceSystem;

import java.util.List;

public class PanelPresenter extends Presenter{
    ConferenceSystem cs;
    IUpdate panel;

    public PanelPresenter(Presenter presenter, IUpdate panel) {
        this.cs = presenter.cs;
        this.panel = panel;
    }

    public void signUpEvent(String eventID) {
        Boolean success = cs.signUpForEvent(eventID);
        if (success) {
            panel.update("succeedSignUpEvent");
        } else {
            panel.update("failSignUpEvent");
        }
    }

    public void waitEvent(String eventID) {
        Boolean success = cs.signUpForEventWaitList(eventID);
        if (success) {
            panel.update("succeedWaitEvent");
        } else {
            panel.update("failWaitEvent");
        }
    }

    public void cancelEvent(String eventID) {
        Boolean success = cs.cancelEvent(eventID);
        if (success) {
            panel.update("succeedCancelEvent");
        } else {
            panel.update("failCancelEvent");
        }
    }

    public void changeEvent(String type, String eventID, String newSetting) {
        Boolean success;
        if (type == "Speaker") {
            success = cs.modifySpeakerForEvent(newSetting, eventID);
        } else if (type == "VIP Status") {
            Boolean VIP = vIPConverter(newSetting);
            success = cs.changeVipStatusOfEvent(eventID, VIP);
        } else {
            success = cs.changeEventCapacity(eventID, newSetting);
        }
        if (success) {
            panel.update("succeedChangeEvent");
        } else {
            panel.update("failChangeEvent");
        }
    }

    private Boolean vIPConverter(String VIP) {
        if (VIP == "Yes") {
            return true;
        } else {
            return false;
        }
    }

    public void createUser(String type, String username, String password) {
        Boolean success;
        if (type == "Speaker") {
            success = cs.createSpeaker(username, password);
        } else if (type == "Organizer") {
            success = cs.createOrganizer(username, password);
        } else if (type == "Attendee") {
            success = cs.createAttendee(username, password);
        } else {
            success = cs.createVipUser(username, password);
        }
        if (success) {
            panel.update("succeedCreateUser");
        } else {
            panel.update("failCreateUser");
        }
    }

    public void createEvent(String eventType, String startTime, String endTime, String speakers,
                            String roomNum, String topic, String capacity, String vipStatus) {
        int type;
        if (eventType == "Party") {
            type = 0;
        } else if (eventType == "Talk") {
            type = 1;
        } else {
            type = 2;
        }
        Boolean success = cs.newEvent(type, startTime, endTime, speakers, topic, roomNum, capacity, vIPConverter(vipStatus));
        if (success) {
            panel.update("succeedCreateEvent");
        } else {
            panel.update("failCreateEvent");
        }
    }

    public void cancelEnrollment(String eventID) {
        Boolean success = cs.cancelEnrollmentInEvent(eventID);
        if (success) {
            panel.update("succeedCancelEnrollment");
        } else {
            panel.update("failCancelEnrollment");
        }
    }

    public void removeWait(String eventID) {
        Boolean success = cs.removeEventFromWaitList(eventID);
        if (success) {
            panel.update("succeedRemoveWait");
        } else {
            panel.update("failRemoveWait");
        }
    }

    public void addRoom(String roomNumber, String capacity) {
        Boolean success = cs.addNewRoom(roomNumber, capacity);
        if (success) {
            panel.update("succeedAddRoom");
        } else {
            panel.update("failAddRoom");
        }
    }

    public List<List<String>> viewAllEvent() {
        return cs.viewEvents();
    }

    public List<List<String>> viewCanWaitlistEvents() {
        return cs.viewCanWaitlistEvents();
    }

    public List<List<String>> viewCanSignUpEvents() {
        return cs.viewCanSignUpEvents();
    }

    public List<List<String>> viewAllRooms() {
        return cs.viewAllRooms();
    }

    public List<List<String>> viewAllSpeaker() {
        return cs.viewAllSpeakers();
    }

    public List<List<String>> viewSignedUpEvents() {
        return cs.viewSignedUpEvents();
    }

    public List<List<String>> viewMyWaitList() {
        return cs.viewMyWaitList();
    }

    public List<List<String>> viewOrganizedEvents() {
        return cs.viewOrganizedEvents();
    }

    public List<List<String>> viewSpeakingEvents() {
        return cs.viewSpeakingEvents();
    }

    public void reSetPass(String newPass){
        Boolean success = cs.resetPassword(newPass);
        if(success){
            panel.update("succeedResetPass");
        }else{
            panel.update("failResetPass");
        }
    }
}