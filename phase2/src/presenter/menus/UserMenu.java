package presenter.menus;

import presenter.IUpdate;
import presenter.windows.LoginWindow;
import presenter.Presenter;
import presenter.factory.PanelFactory;
import presenter.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;
/**
 * The menu all attendees see
 */
public class UserMenu extends JFrame implements IUpdate {
    protected JMenu myProfile;
    protected JMenu schedule;
    protected JMenu signUp;
    protected JMenu viewMyEvent;
    protected JMenuItem logOut;
    protected JMenuItem reSet;
    protected JMenuItem viewAllEvent;
    protected JMenuItem signUpNoWait;
    protected JMenuItem signUpWait;
    protected JMenuItem viewNoWait;
    protected JMenuItem viewWait;
    protected JMenuItem messenger;
    protected JMenuBar menuBar;
    protected PanelFactory panelFactory;
    protected JPanel contentPanel;
    protected Language language;

    /**
     * construct the UserMenu
     * @param language the language used in UserMenu
     * @param presenter the presenter whose presenter is used to initialize the PanelFactory
     */
    public UserMenu(Language language, Presenter presenter) {
        setLayout(new BorderLayout());
        this.language = language;
        panelFactory = new PanelFactory(language, presenter);
        contentPanel = new JPanel();
        contentPanel.add(new JLabel(language.welcome()));
        add(contentPanel, BorderLayout.CENTER);



        menuBar = new JMenuBar();
        myProfile = new JMenu(language.myProfile());
        logOut = new JMenuItem(language.logOut());
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow window = new LoginWindow();
                dispose();
            }
        });
        reSet = new JMenuItem(language.resetPass());
        reSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("reSet");
            }
        });
        myProfile.add(logOut);
        myProfile.add(reSet);
        menuBar.add(myProfile);

        schedule = new JMenu(language.schedule());
        signUp = new JMenu(language.signUp());
        signUpNoWait = new JMenuItem(language.signUpNowait());
        signUpNoWait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("signUpNoWait");
            }
        });
        signUpWait = new JMenuItem(language.signUpWait());
        signUpWait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("signUpWait");
            }
        });
        signUp.add(signUpNoWait);
        signUp.add(signUpWait);
        viewMyEvent = new JMenu(language.viewMyEvent());
        viewNoWait = new JMenuItem(language.viewMyNoWait());
        viewNoWait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("viewNoWait");
            }
        });
        viewWait = new JMenuItem(language.viewMyWait());
        viewWait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("viewWait");
            }
        });
        viewMyEvent.add(viewNoWait);
        viewMyEvent.add(viewWait);
        viewAllEvent = new JMenuItem(language.viewAllEvent());
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

        messenger = new JMenuItem(language.messenger());
        messenger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presenter.type == 2){
                    presenter.openMessenger(2);
                }else if(presenter.type == 1){
                    presenter.openMessenger(1);
                }else{
                    presenter.openMessenger(3);
                }
            }
        });
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
