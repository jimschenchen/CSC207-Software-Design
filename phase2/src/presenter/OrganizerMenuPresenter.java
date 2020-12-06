package presenter;

import controller.ConferenceSystem;
import presenter.factory.JOptionPaneFactory;
import presenter.language.Language;

import javax.swing.*;
import java.awt.*;

public class OrganizerMenuPresenter extends Presenter {
    //private final Language language;
    private ConferenceSystem cs = new ConferenceSystem();
    private IMessage organizerMenu = new OrganizerMenu();

    public OrganizerMenuPresenter(){

    }

   // public void addRoom(String roomNum, String capacity) {
       // boolean success = cs.addNewRoom(roomNum, capacity);
       // if (success) {
           // new JOptionPaneFactory(language).succeedAddRoom();}
       // else {
        //    new JOptionPaneFactory(language).failAddRoom();
       //     }
    //    }
      void createSpeaker(String userName, String password){
        boolean success = cs.createSpeaker(userName, password);
        organizerMenu.messageSuccess(success);
      }
      void assignSpeaker(String speaker, String eventID){
        boolean success = cs.modifySpeakerForEvent(cs.getUserIDbyUserName(speaker), eventID);
        organizerMenu.messageSuccess(success);
      }

}


