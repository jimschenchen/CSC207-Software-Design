package presenter;

import presenter.language.English;
import presenter.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpWindow extends JFrame implements IWindow{
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameText;
    JTextField passwordText;
    JButton okButton;
    JButton backButton;
    Font defaultFont = new Font("Mononspace", 1, 20);
    Language language;

    public SignUpWindow(Language language){
        init();
    }

    public void init(){
        language = new English();
        setLayout(null);
        usernameLabel = new JLabel(language.username());
        usernameLabel.setBounds(50,30, 200, 80);
        usernameLabel.setFont(defaultFont);
        add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(200, 30, 250,80);
        usernameText.setFont(defaultFont);
        add(usernameText);

        passwordLabel = new JLabel(language.password());
        passwordLabel.setBounds(50, 160, 200, 80);
        passwordLabel.setFont(defaultFont);
        add(passwordLabel);

        passwordText = new JTextField();
        passwordText.setBounds(200, 160, 250, 80);
        passwordText.setFont(defaultFont);
        add(passwordText);

        okButton = new JButton(language.ok());
        okButton.setBounds(100, 325, 100,75);
        okButton.setFont(defaultFont);
        add(okButton);

        backButton = new JButton(language.back());
        backButton.setBounds(300, 325, 100,75);
        backButton.setFont(defaultFont);
        add(backButton);

        setTitle(language.titleSignUp());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocation(450,200);
        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow loginWindow = new LoginWindow();
            }
        });

    }


}