package presenter;

import entity.Organizer;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

public class OrganizerMenu extends UserMenu{
    JMenuItem createRoom, optionSpeaker, createEvent, orgEvents, myEvents;
    JMenu events;
    public OrganizerMenu() {
       super();
       this.mySchedule.remove(this.m6);
       this.events = new JMenu("Events");
       this.createEvent = new JMenuItem("Create new Event");
       this.orgEvents = new JMenuItem("Organized Events");
       this.myEvents = new JMenuItem("My Events");
       events.add(createEvent);
       events.add(orgEvents);
       events.add(myEvents);
       mySchedule.add(events);
       this.createRoom = new JMenuItem("Create a new Room");
       this.optionSpeaker = new JMenuItem("Speaker options");
       JMenuItem createSpeaker = new JMenuItem("Create a new Speaker");
       JMenuItem assignSpeaker = new JMenuItem("Assign a new Speaker");
       optionSpeaker.add(createSpeaker);
       optionSpeaker.add(assignSpeaker);
       eventHandler handler2 = new eventHandler();
       //I don't know how to implement the handler for new items
        //Could you please help me?
    }

}
