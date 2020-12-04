package presenter;

import presenter.factory.JOptionPaneFactory;
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
    Font defaultFont = new Font("Mononspace", 1, 25);
    Language language;
    JOptionPaneFactory windowFactory;
    Presenter presenter = new Presenter();
    IWindow window = this;

    public SignUpWindow(Language language) {
        this.language = language;
        windowFactory = new JOptionPaneFactory(language);
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

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("OK");
            }
        });




        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               update("Back");
            }
        });
    }

    private void signUp(){
        String username = usernameText.getText();
        String password = passwordText.getText();
        presenter.signup(username, password, window);
    }

    public void update(String action){
        if(action == "Back"){ //back to the login page
            LoginWindow loginWindow = new LoginWindow(language);
            dispose();
        }else if(action == "CreateAccount"){
            windowFactory.createAccountMessage();
        }else if (action == "Error"){
            windowFactory.errorMessage();
        }
    }


}