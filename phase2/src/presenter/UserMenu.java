package presenter;

import javax.swing.*;
import java.awt.*;

public class UserMenu extends JFrame{
        private JButton button1, button2, button3;
        private static JPanel cards;
        public static JFrame createMainWindow(){
            //Creates and sets up the main window
            JFrame mainFrame = new JFrame();
            mainFrame.setPreferredSize(new Dimension(1000, 1000));
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setBackground(Color.white);
            mainFrame.setVisible(true);
            return mainFrame;
        }
        public void createMenu(){
            JFrame mainFrame = createMainWindow();
            JMenuBar mb = new JMenuBar();
            JMenu menu = new JMenu("UserMenu");
            JMenuItem m1 = new JMenuItem("Messenger");
            JMenu mySchedule  = new JMenu("Schedule");
            menu.add(m1);
            mainFrame.setJMenuBar(mb);
            JMenu myProfile = new JMenu("My Profile");
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
        }






}
