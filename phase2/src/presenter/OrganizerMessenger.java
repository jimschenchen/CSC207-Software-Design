package presenter;

import presenter.language.Language;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * the organizer messenger window
 */

public class OrganizerMessenger extends MessengerWindow {

    /**
     * Construct the Organizer messenger window
     *
     */
    OrganizerMessenger(Presenter presenter, Language language){
        super(presenter, language);
    }

    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();

        if (src == send){
            String title = JOptionPane.showInputDialog(language.enterTitleMes());
            String message = msgSend.getText();
            String[] buttons = {language.mesAllSpeakers(), language.mesOneSpeaker(), language.mesAllAttendees(),
                    language.mesOneAttendees()};
            int i = JOptionPane.showOptionDialog(null, language.sendTo(),
                    language.messengerOptions(),
                    JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[3]);
            if (i == 0){
                super._msgPresenter.messageAllSpeakers(title,message);
            }
            if (i == 1){
                String username = JOptionPane.showInputDialog(language.enterNameSpeaker());
                _msgPresenter.messageOneSpeaker(title, message, username);
            }
            if (i == 2){
                _msgPresenter.messageAllAttendeeOrganizer(title, message);
            }
            if (i == 3){
                String username = JOptionPane.showInputDialog(language.enterNameAttendee());
                _msgPresenter.messageOneAttendee(title, message, username);
            }
            msgSend.setText(language.writeNewMes());
        }
        if (src == logOut){
            System.exit(0);
        }
    }

}
