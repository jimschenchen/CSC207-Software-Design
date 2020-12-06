package presenter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

class MessengerWindow extends JFrame implements ActionListener, IMessage {
    static JTextArea msgRec = new JTextArea(100, 50);
    static JTextArea msgSend = new JTextArea(100, 50);
    JButton send = new JButton("Send");
    JButton rcv = new JButton("Received Messages");
    JButton snd = new JButton("Sent Messages");
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
        rcv.setSize(200, 200);
        msgRec.setLayout(new FlowLayout());
        msgRec.add(rcv);
        msgRec.add(snd);
        rcv.addActionListener(this);
        snd.addActionListener(this);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String title = JOptionPane.showInputDialog("Enter the title of your message");

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
                _msgPresenter.messageAllAttendees(title, message, id);
            }
            if (optionPane == JOptionPane.NO_OPTION){
                String username = JOptionPane.showInputDialog("Enter the username of the attendee");
                _msgPresenter.messageOneAttendee(title, message, username);
            }
            msgSend.setText("Write new message");
        }
        if (src == logOut){
            System.exit(0);
        }
        if (src == organizer){
            //Here we need to check if user is an Organizer through presenter
            OrganizerMessenger org = new OrganizerMessenger();
            dispose();
        }
        if (src == speaker){
            //Here we need to check if user is a speaker through presenter
            SpeakerMessenger spkr = new SpeakerMessenger();
            dispose();
        }
        if (src == rcv) {
            JFrame frame = new JFrame();
            List<List<String>> listOfLists = _msgPresenter.readReceivedMessages();
            DefaultListModel listModel = new DefaultListModel();
            for (List lst : listOfLists) {
                String element = (String) lst.get(0);
                listModel.addElement(element);
            }

            JList list = new JList(listModel);
            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int ind = listOfLists.indexOf(list.getSelectedValue());
                    Object[] replyOrClose = {"Reply",
                            "Close"};
                    int optionPane = JOptionPane.showOptionDialog(new JFrame(),
                            listOfLists.get(ind+1),
                            "Received Email",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            replyOrClose,
                            replyOrClose[1]);
                    if (optionPane == JOptionPane.YES_OPTION){
                        String content = JOptionPane.showInputDialog("Write your message here");
                        String title = JOptionPane.showInputDialog("Choose the title");
                        _msgPresenter.replyTo(content, title);
                    }
                }

            });
            frame.add(list);
            frame.pack();
            frame.setSize(300, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        if (src == snd){
            JFrame frame = new JFrame();
            List<List<String>> listOfLists = _msgPresenter.readSentMessages();
            DefaultListModel listModel = new DefaultListModel();
            for (List lst : listOfLists) {
                String element = (String) lst.get(0);
                listModel.addElement(element);
            }
            JList list = new JList(listModel);
            frame.add(list);
            frame.pack();
            frame.setSize(300, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
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