package presenter;

import presenter.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class UserMenu extends JFrame{
    JFrame mainFrame;
    private JMenu myProfile;
    private JMenu schedule;
    private JMenu signUp;
    private JMenu viewMyEvent;
    private JMenuItem logOut;
    private JMenuItem reSet;
    private JMenuItem viewAllEvent;
    private JMenuItem signUpNoWait;
    private JMenuItem signUpWait;
    private JMenuItem viewNoWait;
    private JMenuItem viewWait;
    private JMenuItem messenger;
    private JMenuBar menuBar;
    private PanelFactory panelFactory = new PanelFactory();
    private JPanel panel;
    private Language language;

    public UserMenu() {
        this.mainFrame = new JFrame();
        mainFrame.setSize(400, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBackground(Color.white);

        panel = new JPanel();
        panel.add( new JLabel("Welcome to the User menu!"));
        setContentPane(panel);

        menuBar = new JMenuBar();
        myProfile = new JMenu("My Profile");
        logOut = new JMenuItem("Log Out");
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginWindow window = new LoginWindow();
            }
        });
        reSet = new JMenuItem("Reset your Account");
        reSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("reSet");
            }
        });
        myProfile.add(logOut);
        myProfile.add(reSet);
        menuBar.add(myProfile);

        schedule = new JMenu("Schedule");
        signUp = new JMenu("Sign Up for an event");
        signUpNoWait = new JMenuItem("Don't have to wait!");
        signUpNoWait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("signUpNoWait");
            }
        });
        signUpWait = new JMenuItem("Get a spot in the waiting list");
        signUpWait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("signUpWait");
            }
        });
        signUp.add(signUpNoWait);
        signUp.add(signUpWait);
        viewMyEvent = new JMenu("View my Event");
        viewNoWait = new JMenuItem("Events not in waiting list");
        viewNoWait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("viewNoWait");
            }
        });
        viewWait = new JMenuItem("Events in waiting list");
        viewWait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("viewWait");
            }
        });
        viewMyEvent.add(viewNoWait);
        viewMyEvent.add(viewWait);
        viewAllEvent = new JMenuItem("View All event");
        viewAllEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("viewAllEvents");
            }
        });
        schedule.add(signUp);
        schedule.add(viewMyEvent);
        schedule.add(viewAllEvent);
        menuBar.add(schedule);

        messenger = new JMenuItem("Messenger");
        menuBar.add(messenger);

        mainFrame.setJMenuBar(menuBar);;

        mainFrame.setVisible(true);

    }

    public void update(String action){
        panel = panelFactory.getPanel(action);
    }

}
