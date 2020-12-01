import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameText;
    JTextField passwordText;
    JButton okButton;
    JButton exitButton;
    JButton signUpButton;
    Font defaultFont = new Font("Mononspace", 1, 25);
    public LoginWindow(){
        init();
    }

    private void init(){
        setLayout(null);
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(50,20, 200, 80);
        usernameLabel.setFont(defaultFont);
        add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(250, 20, 250,80);
        usernameText.setFont(defaultFont);
        add(usernameText);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(50, 150, 200, 80);
        passwordLabel.setFont(defaultFont);
        add(passwordLabel);

        passwordText = new JTextField();
        passwordText.setBounds(250, 150, 250, 80);
        passwordText.setFont(defaultFont);
        add(passwordText);

        signUpButton = new JButton("Sign up for free now!");
        signUpButton.setBounds(50, 275, 500, 80);
        signUpButton.setFont(defaultFont);
        add(signUpButton);

        okButton = new JButton("OK");
        okButton.setBounds(100, 375, 100,75);
        okButton.setFont(defaultFont);
        add(okButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(300, 375, 100,75);
        exitButton.setFont(defaultFont);
        add(exitButton);

        setTitle("The Best Conference in the World");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocation(450,200);
        setVisible(true);
    }
}
