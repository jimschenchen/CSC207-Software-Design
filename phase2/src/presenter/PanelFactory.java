package presenter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFactory {
    private Object JPanel;

    public JPanel getPanel(int i) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        if (i == 4) {
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
        return (javax.swing.JPanel) JPanel;
    }
}

