package presenter;

import javax.swing.*;

public class SpeakerMenu extends UserMenu{
    JMenuItem viewMyTalks;
    JMenu myTalks;
    public SpeakerMenu(){
        super();
        this.myTalks = new JMenu();
        myTalks.add(this.viewMyTalks = new JMenuItem());
        this.menu.add(myTalks);
        //I don't know how to implement the handler for new items
        //Could you please help me?
        // item.addlistener(new .....)
    }
}
