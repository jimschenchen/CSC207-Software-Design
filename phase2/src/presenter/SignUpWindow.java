import javax.swing.*;
import java.awt.*;

public class SignUpWindow extends JFrame{
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameText;
    JTextField passwordText;
    JButton okButton;
    JButton exitButton;
    Font defaultFont = new Font("Mononspace", 1, 25);

    public SignUpWindow(){
        init();
    }

    private void init(){
        setLayout(null);
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(50,30, 200, 80);
        usernameLabel.setFont(defaultFont);
        add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(200, 30, 250,80);
        usernameText.setFont(defaultFont);
        add(usernameText);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(50, 160, 200, 80);
        passwordLabel.setFont(defaultFont);
        add(passwordLabel);

        passwordText = new JTextField();
        passwordText.setBounds(200, 160, 250, 80);
        passwordText.setFont(defaultFont);
        add(passwordText);

        okButton = new JButton("OK");
        okButton.setBounds(100, 325, 100,75);
        okButton.setFont(defaultFont);
        add(okButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(300, 325, 100,75);
        exitButton.setFont(defaultFont);
        add(exitButton);

        setTitle("You are signing up!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocation(450,200);
        setVisible(true);
    }


}