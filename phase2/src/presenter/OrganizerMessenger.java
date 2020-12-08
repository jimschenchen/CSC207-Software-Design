package presenter;

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
    OrganizerMessenger(Presenter presenter){
        super(presenter);
    }

    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();

        if (src == send){
            String title = JOptionPane.showInputDialog("Enter the title of your message");
            String message = msgSend.getText();
            String[] buttons = {"Message all speakers", "Message one speaker", "Message all attendees",
                    "Message attendee"};
            int i = JOptionPane.showOptionDialog(null, "Who do you want to send this message to?",
                    "Message options",
                    JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[3]);
            if (i == 0){
                super._msgPresenter.messageAllSpeakers(title,message);
            }
            if (i == 1){
                String username = JOptionPane.showInputDialog("Enter the username of the speaker");
                _msgPresenter.messageOneSpeaker(title, message, username);
            }
            if (i == 2){
                _msgPresenter.messageAllAttendeeOrganizer(title, message);
            }
            if (i == 3){
                String username = JOptionPane.showInputDialog("Enter the username of an attendee");
                _msgPresenter.messageOneAttendee(title, message, username);
            }
            msgSend.setText("Write new message");
        }
        if (src == logOut){
            System.exit(0);
        }
    }

}
