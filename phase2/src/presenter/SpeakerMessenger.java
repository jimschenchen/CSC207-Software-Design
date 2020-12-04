package presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SpeakerMessenger extends MessengerWindow {
    SpeakerMessenger(){
        super();
        super.bar.remove(options);
    }
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == send) {
            String message = msgSend.getText();
            String[] buttons = {"Message all attendees of my events", "Message one attendee of my event", "Message all attendees",
                    "Message attendee"};
            int i = JOptionPane.showOptionDialog(null, "Who do you want to send this message to?",
                    "Message options",
                    JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[3]);
            if (i == 0){
                super._msgPresenter.MessageAllAttendeesOfSpeakerEvent(message);
            }
            if (i == 1){
                String username = JOptionPane.showInputDialog("Enter the username of the speaker");
                _msgPresenter.MessageOneSpeaker(message, username);
            }
            if (i == 2){
                String id = JOptionPane.showInputDialog("Enter the id of the event");
                _msgPresenter.MessageAllAttendees(message, id);
            }
            if (i == 3){
                String username = JOptionPane.showInputDialog("Enter the username of the attendee");
                _msgPresenter.MessageOneAttendee(message, username);
            }
            msgSend.setText("Write new message");

        }
    }
}
