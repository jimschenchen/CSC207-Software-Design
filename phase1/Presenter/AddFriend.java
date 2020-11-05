import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriend extends JFrame implements ActionListener {
    JTextField username = new JTextField();
    JButton submit = new JButton();
    AddFriend() {
        JTextField username = new JTextField();
        JButton submit = new JButton();
        this.setTitle("Which user do you wanna add?");
        username.setPreferredSize(new Dimension(250, 50));
        username.setText("Put the username here");
        this.add(username);
        submit.setText("Submit");
        this.add(submit);
        submit.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(250, 180);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit){
            String username_input = username.getText();
            //We need to know the Use Case class name and corresponding method
        }}}