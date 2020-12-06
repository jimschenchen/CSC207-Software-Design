package presenter;

import entity.Organizer;
import presenter.language.Language;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

public class OrganizerMenu extends UserMenu{
    JMenu create;
    JMenuItem createUser;
    JMenuItem createRoom;
    JMenuItem createEvent;
    JMenuItem changeEventSetting;
    JMenuItem viewOrganizedEvent;
    JMenu organizedEvent;
    public OrganizerMenu(Language language, Presenter presenter) {
        super(language, presenter);
     create = new JMenu("Create: ");
     createUser = new JMenuItem("New User");
     createUser.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             update("createUser");
         }
     });
     createRoom = new JMenuItem("New Room");
     createRoom.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             update("addRoom");
         }
     });
     createEvent = new JMenuItem("New Event");
     createEvent.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             update("createEvent");
         }
     });
     create.add(createUser);
     create.add(createEvent);
     create.add(createRoom);
     menuBar.add(create);

     organizedEvent = new JMenu("Organized Event");
     changeEventSetting = new JMenuItem("Change Event Setting");
        changeEventSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("changeEventSetting");
            }
        });
     viewOrganizedEvent = new JMenuItem("View Organized Event");
     viewOrganizedEvent.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             update("viewOrganizedEvent");
         }
     });
     organizedEvent.add(changeEventSetting);
     organizedEvent.add(viewOrganizedEvent);
     menuBar.add(organizedEvent);

    }

    public void update(String action){
        contentPanel.removeAll();
        contentPanel.add(panelFactory.getPanel(action));
        contentPanel.validate();
        contentPanel.repaint();
    }


}



