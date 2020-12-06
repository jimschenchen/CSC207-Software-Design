package presenter;

import entity.Organizer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

public class OrganizerMenu extends UserMenu{
    JMenuItem createRoom, optionSpeaker, createEvent, orgEvents, myEvents, createSpeaker, assignSpeaker;
    JMenu events;
    OrganizerMenuPresenter orgPresenter = new OrganizerMenuPresenter();
    public OrganizerMenu() {
       super();
       super.mySchedule.remove(super.m6);
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
       createSpeaker = new JMenuItem("Create a new Speaker");
       assignSpeaker = new JMenuItem("Assign a new Speaker");
       optionSpeaker.add(createSpeaker);
       optionSpeaker.add(assignSpeaker);
       createEvent.addActionListener(this);
       orgEvents.addActionListener(this);
       myEvents.addActionListener(this);
       createSpeaker.addActionListener(this);
       assignSpeaker.addActionListener(this);
    }

   public void actionPerformed(ActionEvent event){
       Object src = event.getSource();
       if (src == createSpeaker){
          String username = JOptionPane.showInputDialog("Enter the username of the speaker");
          String password = JOptionPane.showInputDialog("Enter the username of the password");
          orgPresenter.createSpeaker(username, password);
       }
       if (src == assignSpeaker){
          String speaker = JOptionPane.showInputDialog("Enter the username of the speaker");
          String eventID = JOptionPane.showInputDialog("Enter Event id");
          orgPresenter.assignSpeaker(speaker, eventID);
       }
       if (src == createRoom){
          String roomNum = JOptionPane.showInputDialog("Enter the room number");
          String capacity = JOptionPane.showInputDialog("Enter the room capacity");
         // orgPresenter.addRoom(roomNum, capacity);
          //I'll leave it for you, if you think it is better to implement through factory you can change this
       }
       if (src == createEvent){
          //
       }
       if (src == orgEvents){
          //
       }
       if (src == myEvents){
          //I'll finish it tmrw!
       }
   }
}



