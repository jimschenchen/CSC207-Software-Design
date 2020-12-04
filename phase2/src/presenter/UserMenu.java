package presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

public class UserMenu extends JFrame implements IWindow{
        UserMenuPresenter _presenter;
        final JFrame mainFrame;
        private JButton button1, button2, button3;
        private JMenu myProfile;
    JMenu mySchedule;
    JMenu menu;
        private JMenuItem m4;
    private JMenuItem m5;
    JMenuItem m6;
    private JMenuItem m7;
    private JMenuItem m8;
    private JMenuItem m9;
        private static JPanel cards;

        public UserMenu() {
            //Creates and sets up the main window
            this.mainFrame = new JFrame();
            mainFrame.setPreferredSize(new Dimension(1000, 1000));
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setBackground(Color.white);
            mainFrame.setVisible(true);
            JPanel p = new JPanel();
            JLabel lab1 = new JLabel("User Name", JLabel.LEFT);
            p.setLayout(new FlowLayout());
            p.add(lab1 = new JLabel("Welcome to the User menu!"));
            mainFrame.getContentPane().add(p);
            //createMenu
            JMenuBar mb = new JMenuBar();
            this.menu = new JMenu("UserMenu");
            JMenuItem m1 = new JMenuItem("Messenger");
            this.mySchedule = new JMenu("Schedule");
            menu.add(m1);
            mainFrame.setJMenuBar(mb);
            this.myProfile = new JMenu("My Profile");
            JMenuItem m4 = new JMenuItem("Change Password");
            JMenuItem m5 = new JMenuItem("SignOut");
            JMenuItem m6 = new JMenuItem("Events");
            JMenuItem m7 = new JMenuItem("My Schedule");
            JMenuItem m8 = new JMenuItem("Sign up for a new event");
            JMenuItem m9 = new JMenuItem("Cancel an event");
            mySchedule.add(m6);
            mySchedule.add(m7);
            mySchedule.add(m8);
            mySchedule.add(m9);
            menu.add(mySchedule);
            myProfile.add(m4);
            myProfile.add(m5);
            menu.add(myProfile);
            mb.add(menu);
            eventHandler handler = new eventHandler();
            m4.addActionListener(handler);
            m5.addActionListener(handler);
            m6.addActionListener(handler);
            m7.addActionListener(handler);
            m8.addActionListener(handler);
            m9.addActionListener(handler);
        }



    class eventHandler implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent event) {
                int i = 0;
                if (event.getSource() == m4){
                    i = 4;
                }
                else if (event.getSource()== m5){
                    i = 5;
                }
                else if (event.getSource() == m6){
                   i = 6;
                }
                else if (event.getSource() == m7){
                   i = 7;
                }
                else if (event.getSource() == m8){
                   i = 8;
                }
                else if (event.getSource() == m9){
                    i = 9;
                }
                _presenter.menuItemClicked(i);
            }
        }

        public void panelReplaced(JPanel panel){
             mainFrame.getContentPane().removeAll();
             mainFrame.getContentPane().add(panel);
    }

        @Override
        public void update(String type) {
           PanelFactory factory = new PanelFactory();
           JPanel panel = factory.getPanel("type");
           panelReplaced(panel);

        }
}
