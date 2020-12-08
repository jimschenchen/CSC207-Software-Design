package presenter;

import presenter.language.Language;

import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * the speaker messenger window
 */

public class SpeakerMessenger extends MessengerWindow {
    /**
     * Construct the Speaker messenger window
     *
     */
    SpeakerMessenger(Presenter presenter,Language language){
        super(presenter, language);
    }
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == send) {
            String title = JOptionPane.showInputDialog(language.enterTitleMes());
            String message = msgSend.getText();
            String[] buttons = {language.mesAllAttendeesEvent(), language.mesOneAttendeeEvent()};
            int i = JOptionPane.showOptionDialog(null, language.sendTo(),
                    language.messageInformation(),
                    JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[1]);
            if (i == 0){
                super._msgPresenter.messageAllAttendeesOfSpeakerEvent(title, message);
            }
            if (i == 1){
                String username = JOptionPane.showInputDialog(language.enterNameAttendee());
                String id = JOptionPane.showInputDialog(language.enterIdEvent());
                _msgPresenter.messageOneSpecificAttendee(title, message, id, username);
            }
            msgSend.setText(language.writeNewMes());
        }
    }
}
