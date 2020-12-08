package presenter;

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
    SpeakerMessenger(Presenter presenter){
        super(presenter);
    }
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == send) {
            String title = JOptionPane.showInputDialog("Enter the title of your message");
            String message = msgSend.getText();
            String[] buttons = {"Message all attendees of my events", "Message one attendee of my event"};
            int i = JOptionPane.showOptionDialog(null, "Who do you want to send this message to?",
                    "Message information",
                    JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[1]);
            if (i == 0){
                super._msgPresenter.messageAllAttendeesOfSpeakerEvent(title, message);
            }
            if (i == 1){
                String username = JOptionPane.showInputDialog("Enter the username of the attendee");
                String id = JOptionPane.showInputDialog("Enter the id of the event");
                _msgPresenter.messageOneSpecificAttendee(title, message, id, username);
            }
            msgSend.setText("Write new message");
        }
    }
}
