package presenter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}

