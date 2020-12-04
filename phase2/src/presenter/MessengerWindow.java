package presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.showMessageDialog;

class MessengerWindow extends JFrame implements ActionListener, FocusListener, IMessage {
    static JTextArea msgRec = new JTextArea(100, 50);
    static JTextArea msgSend = new JTextArea(100, 50);
    JButton send = new JButton("Send");
    JScrollPane pane2, pane1;
    JMenu messenger = new JMenu("Messenger");
    JMenuItem logOut = new JMenuItem("Close");
    JMenuBar bar = new JMenuBar();
    JMenu options = new JMenu("Options");
    JMenuItem organizer = new JMenuItem("Organizer Options");
    JMenuItem speaker = new JMenuItem("Speaker Options");
    JMenu messageOptions = new JMenu("Messenger Options");
    JMenuItem viewMessages = new JMenuItem("View My Messages");
    MessengerPresenter _msgPresenter = new MessengerPresenter();


    public MessengerWindow(){
        super("Message");
        setBounds(0, 0, 407, 495);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        msgRec.setEditable(false);
        msgRec.setBackground(Color.WHITE);
        msgRec.setForeground(Color.DARK_GRAY);
        msgRec.addFocusListener(this);
        msgRec.setText("");

        msgRec.setWrapStyleWord(true);
        msgRec.setLineWrap(true);

        pane2 = new JScrollPane(msgRec);
        pane2.setBounds(0, 0, 400, 200);
        pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane2);

        msgSend.setBackground(Color.WHITE);
        msgSend.setForeground(Color.DARK_GRAY);
        msgSend.setLineWrap(true);
        msgSend.setWrapStyleWord(true);

        msgSend.setText("Write Message here");

        pane1 = new JScrollPane(msgSend);
        pane1.setBounds(0, 200, 400, 200);
        pane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane1);

        send.setBounds(0, 400, 400, 40);
        add(send);
        send.addActionListener(this);

        bar.add(messenger);
        messenger.add(logOut);
        logOut.addActionListener(this);

        bar.add(options);
        options.add(organizer);
        organizer.addActionListener(this);
        options.add(speaker);
        speaker.addActionListener(this);

        setJMenuBar(bar);
        messageOptions.add(viewMessages);
        viewMessages.addActionListener(this);
        bar.add(messageOptions);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == send){
            String message = msgSend.getText();
            Object[] options = {"All attendees",
                    "One attendee"};
            int optionPane = JOptionPane.showOptionDialog(this,
                    "Who do you want to send this message to?",
                    "MessageInformation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (optionPane == JOptionPane.YES_OPTION){
                String id = JOptionPane.showInputDialog("Enter the id of the event");
                _msgPresenter.MessageAllAttendees(message, id);
            }
            if (optionPane == JOptionPane.NO_OPTION){
                String username = JOptionPane.showInputDialog("Enter the username of the attendee");
                _msgPresenter.MessageOneAttendee(message, username);
            }
            msgSend.setText("Write new message");
        }
        if (src == logOut){
            System.exit(0);
        }
        if (src == organizer){
            OranizerMessenger org = new OrganizerMessenger();
            dispose();
        }
        if (src == speaker){
            SpeakerMessengger spkr = new SpeakerMessenger();
            dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == msgRec){
            _msgPresenter.SeeReceivedMessages();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == msgRec){
            _msgPresenter.SeeSentMessages();
        }
    }


    public void messageSuccess(boolean success){
        if (success){
            showMessageDialog(null, "Your message was sent successfully!");

        }
        else{
            showMessageDialog(null, "Oops, something went wrong!");
        }
    }
}