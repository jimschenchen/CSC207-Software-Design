import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageSender extends JFrame implements ActionListener {
    JTextField username = new JTextField();
    JButton send = new JButton();
    MessageSender() {
        JTextField username = new JTextField();
        JButton send = new JButton();
        this.setTitle("Write your message here");
        username.setPreferredSize(new Dimension(250, 50));
        username.setText("My message");
        this.add(username);
        send.setText("Send");
        this.add(send);
        send.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(250, 180);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send){
            String username_input = username.getText();
            //We need to know the Use Case class name and corresponding method
        }}}

