package presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MessengerWindow extends JFrame implements ActionListener, FocusListener, KeyListener {
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


    public MessengerWindow(){
        super("Message");
        setBounds(0, 0, 407, 495);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        msgRec.setEditable(false);
        msgRec.setBackground(Color.BLACK);
        msgRec.setForeground(Color.WHITE);
        msgRec.addFocusListener(this);
        msgRec.setText("");

        msgRec.setWrapStyleWord(true);
        msgRec.setLineWrap(true);

        pane2 = new JScrollPane(msgRec);
        pane2.setBounds(0, 0, 400, 200);
        pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane2);

        msgSend.setBackground(Color.DARK_GRAY);
        msgSend.setForeground(Color.WHITE);
        msgSend.setLineWrap(true);
        msgSend.setWrapStyleWord(true);

        msgSend.setText("Write Message here");
        msgSend.addFocusListener(this);
        msgSend.addKeyListener(this);

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
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}