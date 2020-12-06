package presenter;

import presenter.language.Language;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerMenu extends UserMenu{
    JMenuItem viewMyTalks;
    JMenu myTalks;
    public SpeakerMenu(Language language, Presenter presenter) {
        super(language, presenter);
        this.myTalks = new JMenu(language.talk());
        myTalks.add(this.viewMyTalks = new JMenuItem(language.viewTalks()));
        menuBar.add(myTalks);
        viewMyTalks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update("viewTalks");
                ;
            }
        });
    }
    public void update(String action){
        contentPanel.removeAll();
        contentPanel.add(panelFactory.getPanel(action));
        contentPanel.validate();
        contentPanel.repaint();
    }
}
