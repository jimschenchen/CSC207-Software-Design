package controller;

import entity.Attendee;
import entity.Organizer;
import entity.Speaker;
import entity.VipUser;
import gateway.Gateway;

public class OrganizerSystem {
    public Organizer org;

    public Organizer getOrg() {
        return org;
    }

    public Speaker CreateSpeaker(String password, String name, Gateway gw) {
        return new Speaker(gw.getNextUserId(), password, name);
    }

    public Attendee CreateAttendee(String password, String name, Gateway gw) {
        return new Attendee(gw.getNextUserId(), password, name);
    }

    public VipUser CreateVIP(String password, String name, Gateway gw) {
        return new VipUser(gw.getNextUserId(), password, name);
    }
}
