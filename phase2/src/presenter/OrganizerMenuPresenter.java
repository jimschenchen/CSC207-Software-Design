package presenter;

import controller.ConferenceSystem;
import presenter.factory.JOptionPaneFactory;
import presenter.language.Language;

import javax.swing.*;
import java.awt.*;

public class OrganizerMenuPresenter extends Presenter {
    private final Language language;
    private ConferenceSystem cs = new ConferenceSystem();
    private IWindow userMenu;

    public OrganizerMenuPresenter(IWindow userMenu, Language language){
        this.userMenu = userMenu;
        this.language = language;
    }

    public void addRoom(String roomNum, String capacity) {
        boolean success = cs.addNewRoom(roomNum, capacity);
        if (success) {
            new JOptionPaneFactory(language).succeedAddRoom();}
        else {
            new JOptionPaneFactory(language).failAddRoom();
            }
        }
}


