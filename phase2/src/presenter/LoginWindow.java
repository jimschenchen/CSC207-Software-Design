package presenter;

import presenter.language.English;
import presenter.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame  {
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameText;
    JTextField passwordText;
    JButton okButton;
    JButton exitButton;
    JButton signUpButton;
    JButton languageButton;
    Font defaultFont = new Font("Mononspace", 1, 25);
    Font specialFont = new Font("Mononspace", 1, 20);
    Language language;

    public LoginWindow(){//The language is English By default
        language = new English();
        init();
    }

    public LoginWindow(Language language){
        this.language = language;
        init();
    }

    private void init(){
        JPanel loginPanel= new JPanel();
        setContentPane(loginPanel);
        loginPanel.setLayout(new GridLayout(4,2,0,50));


        usernameLabel = new JLabel(language.username());
        usernameLabel.setFont(defaultFont);
        loginPanel.add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setFont(defaultFont);
        loginPanel.add(usernameText);

        passwordLabel = new JLabel(language.password());
        passwordLabel.setFont(defaultFont);
        loginPanel.add(passwordLabel);

        passwordText = new JTextField();
        passwordText.setFont(defaultFont);
        loginPanel.add(passwordText);

        signUpButton = new JButton(language.signUpAccount());
        signUpButton.setFont(specialFont);
        loginPanel.add(signUpButton);

        languageButton = new JButton(language.language());
        languageButton.setFont(specialFont);
        loginPanel.add(languageButton);

        okButton = new JButton(language.ok());
        okButton.setFont(defaultFont);
        loginPanel.add(okButton);

        exitButton = new JButton(language.exit());
        exitButton.setFont(defaultFont);
        loginPanel.add(exitButton);

        loginPanel.setVisible(true);



        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpWindow signUpWindow = new SignUpWindow(language);
                dispose();
            }
        });

        setTitle(language.titleLogin());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocation(450,200);
        setVisible(true);



    }


}

