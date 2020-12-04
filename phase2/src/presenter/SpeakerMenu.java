package presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerMenu extends UserMenu{
    JMenuItem viewMyTalks;
    JMenu myTalks;
    public SpeakerMenu() {
        super();
        this.myTalks = new JMenu();
        myTalks.add(this.viewMyTalks = new JMenuItem());
        this.menu.add(myTalks);
        viewMyTalks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _presenter.menuItemClicked(14);
                ;
            }
        });
    }
}
