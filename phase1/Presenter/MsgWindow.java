import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MsgWindow extends JFrame implements ActionListener{
    JButton friend = new JButton("My Friends' list");
    JButton friended = new JButton("Add friends");
    JButton viewer = new JButton("My messages");
    JButton sender = new JButton("Send Message");
    JPanel friendlist = new JPanel();
    JScrollPane scrollPane = new JScrollPane(friendlist,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    MsgWindow(){
        friend.setBounds(200,100, 100, 50);
        friended.setBounds(200,100, 100, 50);
        viewer.setBounds(200,100, 100, 50);
        sender.setBounds(200,100, 100, 50);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        friendlist.setLayout(new BorderLayout(1,1));
        friendlist.setBackground(Color.white);
        friendlist.add(BorderLayout.NORTH, new JLabel("My Friends")); //ScrollPane would represent the list of all potential users can't be implemented without entities
        this.add(friended);
        this.add(viewer);
        this.add(sender);
        this.add(scrollPane);
        this.setLayout(new FlowLayout());
        this.setTitle("Welcome, Username!"); //Username should be changes to User.username()
        this.setVisible(true);
        this.setSize(420,420);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewer.addActionListener(this);
        sender.addActionListener(this);
        friended.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == friended){
            new AddFriend();
        }
        else if(e.getSource() == viewer){
            new JPanel();
            //We need to know how messages work first
        }
        else if(e.getSource() == sender){
            new MessageSender();
        }
    }
}
