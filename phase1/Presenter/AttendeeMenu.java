import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendeeMenu extends JFrame implements ActionListener {
    JButton schedule = new JButton();
    JButton signup = new JButton();
    JButton cancel = new JButton();
    JButton messenger = new JButton();
    Font newButtonFont=new Font(schedule.getFont().getName(),schedule.getFont().getStyle(),30);
    AttendeeMenu(){
        schedule.setPreferredSize(new Dimension(300,180));
        schedule.setText("See the schedule!");
        schedule.setFont(newButtonFont);
        signup.setPreferredSize(new Dimension(300,180));
        signup.setText("Sign up an event!");
        signup.setFont(newButtonFont);
        cancel.setPreferredSize(new Dimension(300,180));
        cancel.setText("Cancel an event");
        cancel.setFont(newButtonFont);
        messenger.setPreferredSize(new Dimension(300,180));
        messenger.setText("Send a message!");
        messenger.setFont(newButtonFont);
        messenger.addActionListener(this);
        this.add(schedule);
        this.add(signup);
        this.add(cancel);
        this.add(messenger);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(500,800);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == messenger){
            new MsgWindow();
        }
        //no schedule was created to sign up for the event or to see the schedule
    }
}
