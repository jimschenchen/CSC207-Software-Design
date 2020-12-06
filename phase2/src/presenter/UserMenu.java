package presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class UserMenu extends JFrame implements ActionListener, IMessage {
        UserMenuPresenter _presenter;
        final JFrame mainFrame;
        private JButton button1, button2, button3;
        private JMenu myProfile; JMenu mySchedule; JMenu menu;
        JMenuItem m1, m4, m5, m6, m7, m8, m9;
        private static JPanel cards;

        public UserMenu() {
            //Creates and sets up the main window
            this.mainFrame = new JFrame();
            mainFrame.setSize(400, 400);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setBackground(Color.white);
            JPanel p = new JPanel();
            JLabel lab1 = new JLabel("User Name", JLabel.LEFT);
            p.setLayout(new FlowLayout());
            p.add(lab1 = new JLabel("Welcome to the User menu!"));
            mainFrame.getContentPane().add(p);
            //createMenu
            JMenuBar mb = new JMenuBar();
            this.menu = new JMenu("UserMenu");
            m1 = new JMenuItem("Messenger");
            this.mySchedule = new JMenu("Schedule");
            menu.add(m1);
            mainFrame.setJMenuBar(mb);
            this.myProfile = new JMenu("My Profile");
            m4 = new JMenuItem("Change Password");
            m5 = new JMenuItem("SignOut");
            m6 = new JMenuItem("Events");
            m7 = new JMenuItem("My Schedule");
            m8 = new JMenuItem("Sign up for a new event");
            m9 = new JMenuItem("Cancel an event");
            mySchedule.add(m6);
            mySchedule.add(m7);
            mySchedule.add(m8);
            mySchedule.add(m9);
            menu.add(mySchedule);
            myProfile.add(m4);
            myProfile.add(m5);
            menu.add(myProfile);
            mb.add(menu);
            m1.addActionListener(this);
            m4.addActionListener(this);
            m5.addActionListener(this);
            m6.addActionListener(this);
            m7.addActionListener(this);
            m8.addActionListener(this);
            m9.addActionListener(this);
            mainFrame.setVisible(true);
        }

        public void actionPerformed(ActionEvent event) {
            Object src = event.getSource();
            if (src == m1){
                _presenter.menuItemClicked(1);
            }
            if (src == m4){
                String pass = JOptionPane.showInputDialog("Enter you new password");
                _presenter.updatePassword(pass);}
            if (src == m5){
                dispose();
                //do we have a log out event?
            }
            if (src == m6){
                JFrame frame = new JFrame();
                List<List<String>> listOfLists  = _presenter.viewEvents();;
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
                //I'll add the buttons tomorrow I am sorry
                //Someone can add it if you want! Rn they can only see the title lmao
            }
            if (src == m7){
                JFrame frame = new JFrame();
                List<List<String>> listOfLists  = _presenter.viewSignedUpEvents();
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
            if (src == m8){
                String id = JOptionPane.showInputDialog("Enter the id of the event");
                _presenter.sgnUpEvent(id);
            }
            if (src == m9){
                String id = JOptionPane.showInputDialog("Enter the id of the event");
                _presenter.cancelEvent(id);
            }

        }

    @Override
    public void messageSuccess(boolean success) {
            if (success){
                showMessageDialog(null, "Success!");
            }
            else{
                showMessageDialog(null, "Oops, something went wrong!");
            }

    }
}
