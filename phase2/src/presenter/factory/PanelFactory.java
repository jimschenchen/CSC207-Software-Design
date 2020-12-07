package presenter.factory;

import presenter.IUpdate;
import presenter.PanelPresenter;
import presenter.Presenter;
import presenter.language.Language;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * PanelFactory is used to make panel needed by the userMenu
 */
public class PanelFactory implements IUpdate {
    private Language language;
    private JOptionPaneFactory optionPaneFactory;
    private PanelPresenter presenter;

    /**
     * @param language the language used in the panel
     * @param presenter the presenter whose ConferenceSystem is used in the panel
     */
    public PanelFactory(Language language, Presenter presenter){
        this.language = language;
        this.optionPaneFactory = new JOptionPaneFactory(language);
        this.presenter = new PanelPresenter(presenter, this);
    }

    /**
     * make a panel based on the instruction
     * @param action the action required to tell the PanelFactory which panel should be made
     * @Return Return the panel used to reset the password when the action is "reSet",
     *         Return the panel..........
     */
    public JPanel getPanel(String action) {
        JPanel panel = new JPanel();
        if(action == "reSet"){
            panel = reSet();
        }else if(action == "viewAllEvents"){
            panel = viewAllEvents(presenter.viewAllEvent());
        }else if(action == "signUpWait"){
            panel = signUpWaitListEvent(presenter.viewCanWaitlistEvents());
        }else if(action == "signUpNoWait"){
            panel = signUpEvent(presenter.viewCanSignUpEvents());
        }else if (action == "addRoom"){
            panel = addRoom();
        }else if (action == "createEvent"){
            panel = createEvent(presenter.viewAllRooms(), presenter.viewAllSpeaker());
        }else if(action == "createUser"){
            panel = createUser();
        }else if (action == "viewNoWait"){
            panel = alreadySignedUpEvents(presenter.viewSignedUpEvents());
        }else if (action == "viewWait"){
            panel = alreadyWaitlistedSignedUpEvents(presenter.viewMyWaitList());
        }else if (action == "viewOrganizedEvent"){
            panel = organizedEvents(presenter.viewOrganizedEvents());
        }else if (action == "changeEvent"){
            panel = changeEvent();
        }else if (action == "viewTalks"){
            panel = viewSpeakingEvent(presenter.viewSpeakingEvents());
        }
        return panel;
    }

    /**
     * @Description: reset everything
     */
    private JPanel reSet(){
        JPanel panel = new JPanel(new BorderLayout(30,30));
        JTextField newPass = new JTextField(language.newPass());
        newPass.setSize(400,300);
        panel.add(newPass, BorderLayout.CENTER);
        JButton okButton = new JButton(language.ok());
        okButton.setSize(200,80);
        panel.add(okButton,BorderLayout.SOUTH);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.reSetPass(newPass.getText());
            }
        });
        return panel;
    }

    /**
     * @Description: see all the events
     */
    private JPanel viewAllEvents(List<List<String>> events) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(events.size()+1, 7, 10,5));
        System.out.println(events);
        panel.add(new JLabel(language.eventType()));
        panel.add(new JLabel(language.title()));
        panel.add(new JLabel(language.eventId()));
        panel.add(new JLabel(language.startAt()));
        panel.add(new JLabel(language.endAt()));
        panel.add(new JLabel(language.duration()));
        panel.add(new JLabel(language.takePlace()));
        panel.add(new JLabel(language.vIPStatus()));
        for (List<String> event: events){
            panel.add(new JLabel(event.get(0)));
            panel.add(new JLabel(event.get(1)));
            panel.add(new JLabel(event.get(2)));
            panel.add(new JLabel(event.get(3)));
            panel.add(new JLabel(event.get(4)));
            panel.add(new JLabel(event.get(5)));
            panel.add(new JLabel(event.get(6)));
            panel.add(new JLabel(event.get(7)));
        }
        return panel;
    }

    /**
     * @Description: sign up wait list event
     */
    private JPanel signUpWaitListEvent(List<List<String>> events){
        // This method includes both viewing and signing up
        GridLayout layout = new GridLayout(events.size()+1,10, 10,5);
        JPanel panel = new JPanel(layout);
        panel.add(new JLabel(language.eventType()));
        panel.add(new JLabel(language.title()));
        panel.add(new JLabel(language.eventId()));
        panel.add(new JLabel(language.startTime()));
        panel.add(new JLabel(language.endTime()));
        panel.add(new JLabel(language.duration()));
        panel.add(new JLabel(language.takePlace()));
        panel.add(new JLabel(language.vIPStatus()));
        panel.add(new JLabel(language.waitinglist()));
        panel.add(new JLabel(language.signUpQuestion()));
        JButton[] buttonArray = new JButton[events.size()];
        int i = 0;
        for (List<String> event: events){
            panel.add(new JLabel(event.get(0)));
            panel.add(new JLabel(event.get(1)));
            panel.add(new JLabel(event.get(2)));
            panel.add(new JLabel(event.get(3)));
            panel.add(new JLabel(event.get(4)));
            panel.add(new JLabel(event.get(5)));
            panel.add(new JLabel(event.get(6)));
            panel.add(new JLabel(event.get(7)));
            panel.add(new JLabel(event.get(9)));
            buttonArray[i] = new JButton(language.yes());
            buttonArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.waitEvent(event.get(2)); // the presenter will be added later
                }
            });
            panel.add(buttonArray[i]);
            i++;
        }
        return panel;
    }

    /**
     * @Description: sign up event
     */
    private JPanel signUpEvent(List<List<String>> events){
        GridLayout layout = new GridLayout(events.size()+1,9, 10,5);
        JPanel panel = new JPanel(layout);
        panel.add(new JLabel(language.eventType()));
        panel.add(new JLabel(language.title()));
        panel.add( new JLabel(language.eventId()));
        panel.add(new JLabel(language.startTime()));
        panel.add(new JLabel(language.endTime()));
        panel.add(new JLabel(language.duration()));
        panel.add(new JLabel(language.takePlace()));
        panel.add(new JLabel(language.vIPStatus()));
        panel.add(new JLabel(language.signUpQuestion()));
        JButton[] buttonArray = new JButton[events.size()];
        int i = 0;
        for (List<String> event: events){
            panel.add(new JLabel(event.get(0)));
            panel.add(new JLabel(event.get(1)));
            panel.add(new JLabel(event.get(2)));
            panel.add(new JLabel(event.get(3)));
            panel.add(new JLabel(event.get(4)));
            panel.add(new JLabel(event.get(5)));
            panel.add(new JLabel(event.get(6)));
            panel.add(new JLabel(event.get(7)));
            buttonArray[i] = new JButton(language.yes());
            buttonArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.signUpEvent(event.get(2)); //the presenter will be added later
                }
            });
            panel.add(buttonArray[i]);
            i++;
        }
        return panel;
    }

    /**
     * @Description: add a room
     */
    private JPanel addRoom() {
        JPanel addRoomPanel = new JPanel();
        JLabel addRoom = new JLabel(language.enterRoomNum());
        JLabel roomCapacity = new JLabel(language.enterRoomCapacity());
        JButton btn = new JButton(language.create());;
        JTextField jtxt=new JTextField(10);
        JTextField cap = new JTextField(5);
        addRoomPanel.setLayout(new GridLayout(5,1));
        addRoomPanel.add(addRoom);
        addRoomPanel.add(jtxt);
        addRoomPanel.add(roomCapacity);
        addRoomPanel.add(cap);
        addRoomPanel.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.addRoom(jtxt.getText(), cap.getText());

        }});
        return addRoomPanel;
    }

    /**
     * @param rooms a list of room ids
     * @param speakers a list of speaker id
     * @Description: create event
     */
    private JPanel createEvent(List<List<String>> rooms, List<List<String>> speakers){
        int length = speakers.size();
        float lengthF = length;
        int numCheck = new Double(Math.ceil(lengthF/2)).intValue();
        System.out.println(speakers.size());
        System.out.println(numCheck);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9 + numCheck ,2,10,5));

        JLabel type = new JLabel(language.eventType());
        panel.add(type);
        JComboBox typeSelected = new JComboBox(new String[]{language.party(), language.talk(),
                language.discussion()});
        typeSelected.setSize(300,100);
        panel.add(typeSelected);

        JLabel start = new JLabel(language.startAt());
        panel.add(start);
        JTextField startTime = new JTextField("YYYY-MM-DD HH:mm");
        panel.add(startTime);

        JLabel end = new JLabel(language.endAt());
        panel.add(end);
        JTextField endTime = new JTextField("YYYY-MM-DD HH:mm");
        panel.add(endTime);

        JLabel topic =  new JLabel(language.topic());
        JTextField topicEntered = new JTextField();
        panel.add(topic);
        panel.add(topicEntered);

        JLabel roomNumber = new JLabel(language.roomNum());
        panel.add(roomNumber);
        String[] roomsArray = new String[rooms.size()];
        int ii = 0;
        for(List<String> room: rooms){
            roomsArray[ii] = room.get(0) + " with capacity "+room.get(1);
            ii++;
        }
        JComboBox roomNumberSelected = new JComboBox(roomsArray);
        panel.add(roomNumberSelected);

        JLabel capacity = new JLabel(language.capacity());
        JTextField capacityEntered = new JTextField();
        panel.add(capacity);
        panel.add(capacityEntered);

        JLabel VIPStatus = new JLabel(language.vIPStatus());
        panel.add(VIPStatus);
        JComboBox VIPStatusSelected = new JComboBox(new String[]{language.yes(), language.no()});
        panel.add(VIPStatusSelected);



        JLabel askSpeaker = new JLabel(language.selectSpeaker());
        panel.add(askSpeaker);
        Panel empty1 = new Panel();
        panel.add(empty1);


        JCheckBox[] checkArray = new JCheckBox[speakers.size()];
        String[] idArray = new String[speakers.size()];

        int i = 0;
        for(List<String> speaker : speakers){
            checkArray[i] = new JCheckBox(speaker.get(0) + " "+ speaker.get(1));
            idArray[i] = speaker.get(0);
            panel.add(checkArray[i]);
            i++;
        }
        if (speakers.size() % 2 == 1){
            Panel empty2 = new Panel();
            panel.add(empty2);
        }

        JButton okButton = new JButton(language.ok());
        panel.add(okButton);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.createEvent(typeSelected.getSelectedIndex(), startTime.getText(),
                        endTime.getText(),
                        idHelper(checkArray, idArray), rooms.get( roomNumberSelected.getSelectedIndex()).get(0),
                        topicEntered.getText(),
                       capacityEntered.getText(),
                        VIPStatusSelected.getSelectedIndex());
            }
        });
        JButton resetButton = new JButton(language.empty());

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime.setText("YYYY-MM-DD HH:mm");
                endTime.setText("YYYY-MM-DD HH:mm");
                topicEntered.setText("");
                capacityEntered.setText("");
                for (JCheckBox cb : checkArray){
                    cb.setSelected(false);
                }
            }
        });

        panel.add(resetButton);

        return panel;
    }

    private String idHelper(JCheckBox[] checkArray, String[] idArray){
        ArrayList<String> idSelected = new ArrayList<>();
        StringBuilder speakersSelected = new StringBuilder();
        int ii = 0;
       for (JCheckBox cb : checkArray){
            if (cb.isSelected()) {
                idSelected.add(idArray[ii]);
            }
            ii++;
        }
       if (idSelected.size() == 1){
           speakersSelected = new StringBuilder(idSelected.get(0));
       }else{
           for (String id: idSelected){
               speakersSelected.append(id).append(",");
               System.out.println(id);
           }
       }
       System.out.println(speakersSelected);
       return speakersSelected.toString();
    }

    /**
     * @Description: create users
     */
    private JPanel createUser(){
        JPanel panel = new JPanel(new GridLayout(4,2));
        JLabel userType = new JLabel(language.userType());
        panel.add(userType);
        JComboBox userTypeSelected = new JComboBox(new String[]{language.speaker(),
               language.organizer(), language.attendee(), "VIP"});
        panel.add(userTypeSelected);
        JLabel username = new JLabel(language.username());
        panel.add(username);
        JTextField usernameEntered = new JTextField();
        panel.add(usernameEntered);
        JLabel password = new JLabel(language.password());
        panel.add(password);
        JTextField passwordEntered = new JTextField();
        panel.add(passwordEntered);
        JButton okButton = new JButton(language.ok());
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.createUser((String) userTypeSelected.getSelectedItem(), usernameEntered.getText(),
                        passwordEntered.getText());
            }
        });
        JButton resetButton = new JButton(language.empty());
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameEntered.setText("");
                passwordEntered.setText("");
            }
        });
        panel.add(okButton);
        panel.add(resetButton);
        return panel;
    }

    private JPanel alreadySignedUpEvents(List<List<String>> events){
        JPanel panel = new JPanel(new GridLayout(events.size()+1, 9, 10,5));
        panel.add(new JLabel(language.eventType()));
        panel.add(new JLabel(language.title()));
        panel.add(new JLabel("ID"));
        panel.add(new JLabel(language.startAt()));
        panel.add(new JLabel(language.endAt()));
        panel.add(new JLabel(language.duration()));
        panel.add(new JLabel(language.takePlace()));
        panel.add(new JLabel(language.vIPStatus()));
        panel.add(new JLabel(language.cancel()));
        JButton[] cancelButtons = new JButton[events.size()];
        int i = 0;
        for (List<String> event: events){
            panel.add(new JLabel(event.get(0)));
            panel.add(new JLabel(event.get(1)));
            panel.add(new JLabel(event.get(2)));
            panel.add(new JLabel(event.get(3)));
            panel.add(new JLabel(event.get(4)));
            panel.add(new JLabel(event.get(5)));
            panel.add(new JLabel(event.get(6)));
            cancelButtons[i] = new JButton(language.reallyQuestion());
            cancelButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.cancelEnrollment(event.get(2));
                }
            });
            panel.add(cancelButtons[i]);
            i++;
        }
        return panel;
    }

    private JPanel alreadyWaitlistedSignedUpEvents(List<List<String>> events){
        JPanel panel = new JPanel(new GridLayout(events.size()+1, 10, 10,5));
        panel.add(new JLabel(language.eventType()));
        panel.add(new JLabel(language.title()));
        panel.add(new JLabel("ID"));
        panel.add(new JLabel(language.startAt()));
        panel.add(new JLabel(language.endAt()));
        panel.add(new JLabel(language.duration()));
        panel.add(new JLabel(language.takePlace()));
        panel.add(new JLabel(language.vIPStatus()));
        panel.add(new JLabel(language.rank()));
        panel.add(new JLabel(language.cancel()));
        JButton[] cancelButtons = new JButton[events.size()];
        int i = 0;
        for (List<String> event: events){
            panel.add(new JLabel(event.get(0)));
            panel.add(new JLabel(event.get(1)));
            panel.add(new JLabel(event.get(2)));
            panel.add(new JLabel(event.get(3)));
            panel.add(new JLabel(event.get(4)));
            panel.add(new JLabel(event.get(5)));
            panel.add(new JLabel(event.get(6)));
            panel.add(new JLabel(event.get(7)));
            cancelButtons[i] = new JButton(language.reallyQuestion());
            cancelButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.removeWait(event.get(2));
                }
            });
            panel.add(cancelButtons[i]);
            i++;
        }
        return panel;
    }

    private JPanel organizedEvents(List<List<String>> events){
        JPanel panel = new JPanel(new GridLayout(events.size()+1, 11, 10,5));
        panel.add(new JLabel(language.eventType()));
        panel.add(new JLabel(language.title()));
        panel.add(new JLabel("ID"));
        panel.add(new JLabel(language.startAt()));
        panel.add(new JLabel(language.endAt()));
        panel.add(new JLabel(language.duration()));
        panel.add(new JLabel(language.takePlace()));
        panel.add(new JLabel(language.vIPStatus()));
        panel.add(new JLabel(language.speaker()));
        panel.add(new JLabel(language.capacity()));
        panel.add(new JLabel(language.cancel()));
        JButton[] cancelButtons = new JButton[events.size()];
        int i = 0;
        for (List<String> event: events){
            panel.add(new JLabel(event.get(0)));
            panel.add(new JLabel(event.get(1)));
            panel.add(new JLabel(event.get(2)));
            panel.add(new JLabel(event.get(3)));
            panel.add(new JLabel(event.get(4)));
            panel.add(new JLabel(event.get(5)));
            panel.add(new JLabel(event.get(6)));
            panel.add(new JLabel(event.get(7)));
            panel.add(new JLabel(event.get(8)));
            panel.add(new JLabel(event.get(9)));
            cancelButtons[i] = new JButton(language.reallyQuestion());
            cancelButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.cancelEvent(event.get(2));
                }
            });
            panel.add(cancelButtons[i]);
            i++;
        }
        return panel;
    }

    /**
     * @Description: change an event
     */
    private JPanel changeEvent(){
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        JLabel eventID = new JLabel(language.eventId());
        panel.add(eventID);
        JTextField eventEntered = new JTextField();
        panel.add(eventEntered);
        JLabel changeItem = new JLabel(language.change());
        panel.add(changeItem);
        JComboBox changeItemSelected = new JComboBox(new String[]{language.speaker(), language.capacity(),
                language.vIPStatus()});
        panel.add(changeItemSelected);
        JLabel newSetting = new JLabel(language.newWord());
        panel.add(newSetting);
        JTextField newSettingEntered = new JTextField();
        panel.add(newSettingEntered);
        JButton resetButton = new JButton(language.empty());
        JButton okButton = new JButton(language.ok());
        panel.add(okButton);
        panel.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newSettingEntered.setText("");
                eventEntered.setText("");
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.changeEvent((String) changeItemSelected.getSelectedItem(), eventEntered.getText(),
                        newSettingEntered.getText());
            }
        });
        return panel;
    }

    /**
     * @Description: look at the speaking events
     */
    private JPanel viewSpeakingEvent(List<List<String>> givingEvent) {
        JPanel panel = new JPanel(new GridLayout(givingEvent.size()+1, 9, 10,5));
        panel.add(new JLabel(language.title()));
        panel.add(new JLabel("ID"));
        panel.add(new JLabel(language.startAt()));
        panel.add(new JLabel(language.endAt()));
        panel.add(new JLabel(language.duration()));
        panel.add(new JLabel(language.takePlace()));
        panel.add(new JLabel(language.vIPStatus()));
        panel.add(new JLabel(language.capacity()));
        panel.add(new JLabel(language.speaker()));
        for (List<String> event: givingEvent){
            panel.add(new JLabel(event.get(0)));
            panel.add(new JLabel(event.get(1)));
            panel.add(new JLabel(event.get(2)));
            panel.add(new JLabel(event.get(3)));
            panel.add(new JLabel(event.get(4)));
            panel.add(new JLabel(event.get(5)));
            panel.add(new JLabel(event.get(6)));
            panel.add(new JLabel(event.get(7)));
            panel.add(new JLabel(event.get(8)));
        }
        return panel;
    }

    /**
     * @Description: update all the actions
     */
    public void update(String action){
       optionPaneFactory.get(action);
    }





}

