package presenter;

import presenter.language.English;
import presenter.language.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpWindow extends JFrame {
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameText;
    JTextField passwordText;
    JButton okButton;
    JButton backButton;
    Font defaultFont = new Font("Mononspace", 1, 25);
    Language language;

    public SignUpWindow(Language language) {
        this.language = language;
        init();
    }

    private void init() {
        JPanel signUpPanel= new JPanel();
        setContentPane(signUpPanel);
        signUpPanel.setLayout(new GridLayout(3,2,0,50));

        usernameLabel = new JLabel(language.username());
        usernameLabel.setFont(defaultFont);
        signUpPanel.add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setFont(defaultFont);
        signUpPanel.add(usernameText);

        passwordLabel = new JLabel(language.password());
        passwordLabel.setFont(defaultFont);
        signUpPanel.add(passwordLabel);

        passwordText = new JTextField();
        passwordText.setFont(defaultFont);
        signUpPanel.add(passwordText);

        okButton = new JButton(language.ok());
        okButton.setFont(defaultFont);
        signUpPanel.add(okButton);

        backButton = new JButton(language.back());
        backButton.setFont(defaultFont);
        signUpPanel.add(backButton);

        setTitle(language.titleSignUp());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocation(450,200);
        setVisible(true);



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow loginWindow = new LoginWindow();
                dispose();
            }
        });
    }
}