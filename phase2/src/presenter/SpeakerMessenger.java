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
        String title = JOptionPane.showInputDialog("Enter the title of your message");

        if (src == send) {
            String message = msgSend.getText();
            String[] buttons = {"Message all attendees of my events", "Message one attendee of my event"};
            int i = JOptionPane.showOptionDialog(null, "Who do you want to send this message to?",
                    "Message options",
                    JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[1]);
            if (i == 0){
                super._msgPresenter.messageAllAttendeesOfSpeakerEvent(title, message);
            }
            if (i == 1){
                String username = JOptionPane.showInputDialog("Enter the username of the speaker");
                String id = JOptionPane.showInputDialog("Enter the id of the event");
                _msgPresenter.messageOneSpecificAttendee(title, message, id, username);
            }
            msgSend.setText("Write new message");
        }
    }
}
