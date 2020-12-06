package presenter;

import presenter.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class UserMenu extends JFrame implements IUpdate{
    JMenu myProfile;
    JMenu schedule;
    JMenu signUp;
    JMenu viewMyEvent;
    JMenuItem logOut;
    JMenuItem reSet;
    JMenuItem viewAllEvent;
    JMenuItem signUpNoWait;
    JMenuItem signUpWait;
    JMenuItem viewNoWait;
    JMenuItem viewWait;
    JMenuItem messenger;
    JMenuBar menuBar;
    PanelFactory panelFactory;
    JPanel contentPanel;
    Language language;

    public UserMenu(Language language, Presenter presenter) {
        setLayout(new BorderLayout());
        this.language = language;
        panelFactory = new PanelFactory(language, presenter);
        add(contentPanel, BorderLayout.CENTER);

        contentPanel = new JPanel();
        contentPanel.add(new JLabel("Welcome!"));


        menuBar = new JMenuBar();
        myProfile = new JMenu("My Profile");
        logOut = new JMenuItem("Log Out");
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow window = new LoginWindow();
                dispose();
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

        setJMenuBar(menuBar);;

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void update(String action){
        contentPanel.removeAll();
        contentPanel.add(panelFactory.getPanel(action));
        contentPanel.validate();
        contentPanel.repaint();
    }




}
