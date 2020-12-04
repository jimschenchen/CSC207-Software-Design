package presenter;

import sun.tools.jps.Jps;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelFactory {
    //Produces the Panel depending on the specified type
    //Add Panel type whenever needed
    private Object JPanel;

    public JPanel getPanel(String string) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        if (string.equals("passwordField")) {
            JPasswordField pass = new JPasswordField();
            pass.setFont(new Font("Tahoma", Font.PLAIN, 34));
            pass.setBounds(373, 35, 609, 67);
            panel.add(pass);
            pass.setColumns(10);
            JButton btn = new JButton("Enter");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char[] txt = pass.getPassword();
                }
            });
        }
        if (string.equals("textField")){
            JTextField txt = new JPasswordField();
            txt.setFont(new Font("Tahoma", Font.PLAIN, 34));
            txt.setBounds(373, 35, 609, 67);
            panel.add(txt);
            txt.setColumns(10);
            JButton btn = new JButton("Enter");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String t = txt.getText();
                }
            });
        }
        return (javax.swing.JPanel) JPanel;
    }


    public JPanel viewEventPanel(ArrayList<ArrayList<String>> allEventList) {
        JPanel viewEventPanel = new JPanel();
        viewEventPanel.setLayout(null);
        TitledBorder tb = BorderFactory.createTitledBorder("Events' Info");
        viewEventPanel.setBorder(tb);
        for (int i = 0; i < allEventList.size(); i++) {
            ArrayList<String> event = allEventList.get(i);
            String eventInfo = event.get(1) + ", " + event.get(0)+ ", with ID "+ event.get(2) + " starts at "
                    + event.get(3) + " ends at " + event.get(4) + " takes place at " +  event.get(5) + " which is "
                    + event.get(6);
            JLabel eventLabel = new JLabel(eventInfo);
            eventLabel.setBounds(0, 50*i, 1000,50);
            viewEventPanel.add(eventLabel);
        }
        return viewEventPanel;
    }

    public JPanel signUpEvent(List<List<String>> events){
        GridLayout layout = new GridLayout(events.size()+1,7, 10,5);
        JPanel panel = new JPanel(layout);
        panel.add(new JLabel("Title"));
        panel.add(new JLabel("Start Time: "));
        panel.add(new JLabel("End time: "));
        panel.add(new JLabel("Duration "));
        panel.add(new JLabel("Location "));
        panel.add(new JLabel("VIP Status: "));
        panel.add(new JLabel("Sign Up?"));
        JButton[] buttonArray = new JButton[events.size()];
        int i = 0;
        for (List<String> event: events){
            panel.add(new JLabel(event.get(0)));
            panel.add(new JLabel(event.get(2)));
            panel.add(new JLabel(event.get(3)));
            panel.add(new JLabel(event.get(4)));
            panel.add(new JLabel(event.get(5)));
            panel.add(new JLabel(event.get(6)));
            buttonArray[i] = new JButton("Yes!");
            buttonArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.signUpEvent(event.get(1));
                }
            });
        }
        return panel;
    }
}

