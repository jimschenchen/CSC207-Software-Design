import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame implements ActionListener {
    JButton signup = new JButton();
    JButton login = new JButton();

    Welcome() {
        this.setTitle("Welcome to our conference!");
        signup.setPreferredSize(new Dimension(300, 200));
        login.setPreferredSize(new Dimension(300, 200));
        signup.setText("Sign Up for Free!");
        login.setText("Log in Now!");
        login.addActionListener(this);
        this.add(signup);
        this.add(login);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(700, 250);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            this.dispose();
            JFrame newWindow = new LogIn();

        }
    }
}
