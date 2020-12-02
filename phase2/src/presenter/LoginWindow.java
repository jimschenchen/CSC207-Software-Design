package presenter;

import presenter.language.English;
import presenter.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//All the windows are the UI
public class LoginWindow extends JFrame implements IWindow{
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameText;
    JTextField passwordText;
    JButton okButton;
    JButton exitButton;
    JButton signUpButton;
    JButton languageButton;
    Font defaultFont = new Font("Mononspace", 1, 25);
    private Presenter presenter = new Presenter();
    Language language;

    public LoginWindow(){//The language is English By default
        language = new English();
        init();
    }

    public LoginWindow(Language inputLanguage){
        language = inputLanguage;
        init();
    }

    private void init() {
        setLayout(null);
        usernameLabel = new JLabel(language.username());
        usernameLabel.setBounds(50, 20, 200, 80);
        usernameLabel.setFont(defaultFont);
        add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(250, 20, 250, 80);
        usernameText.setFont(defaultFont);
        add(usernameText);

        passwordLabel = new JLabel(language.password());
        passwordLabel.setBounds(50, 150, 200, 80);
        passwordLabel.setFont(defaultFont);
        add(passwordLabel);

        passwordText = new JTextField();
        passwordText.setBounds(250, 150, 250, 80);
        passwordText.setFont(defaultFont);
        add(passwordText);

        signUpButton = new JButton(language.signUpAccount());
        signUpButton.setBounds(50, 275, 350, 80);
        signUpButton.setFont(defaultFont);
        add(signUpButton);

        languageButton = new JButton();
        languageButton.setBounds(500, 275, 100, 80);
        languageButton.setFont(defaultFont);
        add(languageButton);

        okButton = new JButton(language.ok());
        okButton.setBounds(100, 375, 100, 75);
        okButton.setFont(defaultFont);
        add(okButton);

        exitButton = new JButton(language.exit());
        exitButton.setBounds(300, 375, 100, 75);
        exitButton.setFont(defaultFont);
        add(exitButton);


        setTitle(language.titleLogin());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocation(450, 200);
        setVisible(true);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpWindow signUpWindow = new SignUpWindow(language);
                dispose();
            }
        });
    }









}

