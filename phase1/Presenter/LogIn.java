import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame implements ActionListener {
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JButton submit = new JButton();
    LogIn(){
        this.setTitle("Welcome Back!");
        username.setPreferredSize(new Dimension(250, 50));
        password.setPreferredSize(new Dimension(250,50));
        username.setText("Put your username here");
        password.setText("Put your password here");
        this.add(username);
        this.add(password);
        submit.setText("Submit");
        this.add(submit);
        submit.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(250,180);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit){
            String username_input = username.getText();
            String password_input = password.getText();
            //We need to know the usecase class name and corresponding method
        }
    }
}
