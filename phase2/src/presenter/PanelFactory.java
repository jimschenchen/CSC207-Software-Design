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
import java.util.Vector;

public class PanelFactory implements IUpdate{
    //Produces the Panel depending on the specified type
    //Add Panel type whenever needed
    private Object JPanel;
    private PanelPresenter presenter;


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
        if (string.equals("textField")) {
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
        if (string.equals("Panel")) {
            return panel;
        }
        return panel;
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

    public JPanel signUpWaitlistEvent(List<List<String>> events){
        // This method includes both viewing and signing up
        GridLayout layout = new GridLayout(events.size()+1,8, 10,5);
        JPanel panel = new JPanel(layout);
        panel.add(new JLabel("Title"));
        panel.add(new JLabel("Start Time: "));
        panel.add(new JLabel("End time: "));
        panel.add(new JLabel("Duration "));
        panel.add(new JLabel("Location "));
        panel.add(new JLabel("VIP Status: "));
        panel.add(new JLabel("Waiting list: "));
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
            panel.add(new JLabel(event.get(7)));
            buttonArray[i] = new JButton("Yes!");
            buttonArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.signUpEvent(event.get(1)); // the presenter will be added later
                }
            });
            panel.add(buttonArray[i]);
            i++;
        }
        return panel;
    }


    public JPanel signUpEvent(List<List<String>> events){
        // This method includes both viewing and signing up. Why? Because I am lazy.
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
                    presenter.signUpEvent(event.get(1)); //the presenter will be added later
                }
            });
            panel.add(buttonArray[i]);
            i++;
        }
        return panel;
    }

    public JPanel AddRoomPanel(String roomNub) {
        JPanel addRoomPanel = new JPanel();
        JLabel addRoom = new JLabel("Enter a new room number you want to add:");
        JLabel roomCapacity = new JLabel("Enter a capacity for this room:");
        JButton btn = new JButton("Create");;
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

    public JPanel createEvent(List<String> rooms, List<List<String>> speakers){
        int length = speakers.size();
        float lengthF = length;
        int numCheck = new Double(Math.ceil(lengthF/2)).intValue();
        System.out.println(speakers.size());
        System.out.println(numCheck);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9 + numCheck ,2,10,5));

        JLabel type = new JLabel("Event Type: ");
        panel.add(type);
        JComboBox typeSelected = new JComboBox(new String[]{"Party", "Talk", "Panel Discussion"});
        typeSelected.setSize(300,100);
        panel.add(typeSelected);

        JLabel start = new JLabel("Start: ");
        panel.add(start);
        JTextField startTime = new JTextField("YYYY-MM-DD HH:mm");
        panel.add(startTime);

        JLabel end = new JLabel("End: ");
        panel.add(end);
        JTextField endTime = new JTextField("YYYY-MM-DD HH:mm");
        panel.add(endTime);

        JLabel topic =  new JLabel("Topic: ");
        JTextField topicEntered = new JTextField();
        panel.add(topic);
        panel.add(topicEntered);

        JLabel roomNumber = new JLabel("Room Number: ");
        panel.add(roomNumber);
        JComboBox roomNumberSelected = new JComboBox((Vector) rooms);
        panel.add(roomNumberSelected);

        JLabel capacity = new JLabel("Capacity: ");
        JTextField capacityEntered = new JTextField();
        panel.add(capacity);
        panel.add(capacityEntered);

        JLabel VIPStatus = new JLabel("VIP status: ");
        panel.add(VIPStatus);
        JComboBox VIPStatusSelected = new JComboBox(new String[]{"Yes", "No"});
        panel.add(VIPStatusSelected);



        JLabel askSpeaker = new JLabel("Select Speaker: ");
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

        JButton okButton = new JButton("OK");
        panel.add(okButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.createEvent((String) typeSelected.getSelectedItem(), startTime.getText(),
                        endTime.getText(),
                        idHelper(checkArray, idArray),(String) roomNumberSelected.getSelectedItem(),
                        topicEntered.getText(),
                       capacityEntered.getText(),
                        (String) VIPStatusSelected.getSelectedItem());
            }
        });
        JButton resetButton = new JButton("Reset");

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
        String speakersSelected = "";
        int ii = 0;
        for (JCheckBox cb : checkArray){
            if (cb.isSelected()){
                speakersSelected = speakersSelected + idArray[ii] + ",";
            }
            ii++;
        }
        return speakersSelected;
    }

    public JPanel createUser(){
        JPanel panel = new JPanel(new GridLayout(4,2));
        JLabel userType = new JLabel("User Type: ");
        panel.add(userType);
        JComboBox userTypeSelected = new JComboBox(new String[]{"Speaker", "Organizer", "Attendee", "VIP"});
        panel.add(userTypeSelected);
        JLabel username = new JLabel("User Name: ");
        panel.add(username);
        JTextField usernameEntered = new JTextField();
        panel.add(usernameEntered);
        JLabel password = new JLabel("Password: ");
        panel.add(password);
        JTextField passwordEntered = new JTextField();
        panel.add(passwordEntered);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.createUser((String) userTypeSelected.getSelectedItem(), usernameEntered.getText(),
                        passwordEntered.getText());
            }
        });
        JButton resetButton = new JButton("Reset");
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

    public JPanel alreadySignedUpEvents(List<List<String>> events){
        JPanel panel = new JPanel(new GridLayout(events.size()+1, 8, 10,5));
        panel.add(new JLabel("Title"));
        panel.add(new JLabel("ID"));
        panel.add(new JLabel("Start at:"));
        panel.add(new JLabel("End at"));
        panel.add(new JLabel("Duration: "));
        panel.add(new JLabel("Location: "));
        panel.add(new JLabel("VIP Status: "));
        panel.add(new JLabel("Cancel"));
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
            cancelButtons[i] = new JButton("Really??!");
            cancelButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.cancelEnrollment(event.get(1));
                }
            });
            i++;
        }
        return panel;
    }

    public JPanel alreadyWaitlistedSignedUpEvents(List<List<String>> events){
        JPanel panel = new JPanel(new GridLayout(events.size()+1, 9, 10,5));
        panel.add(new JLabel("Title"));
        panel.add(new JLabel("ID"));
        panel.add(new JLabel("Start at:"));
        panel.add(new JLabel("End at"));
        panel.add(new JLabel("Duration: "));
        panel.add(new JLabel("Location: "));
        panel.add(new JLabel("VIP Status: "));
        panel.add(new JLabel("My Rank: "));
        panel.add(new JLabel("Cancel"));
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
            cancelButtons[i] = new JButton("Really??!");
            cancelButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.removeWait(event.get(1));
                }
            });
            i++;
        }
        return panel;
    }

    public JPanel organizedEvents(List<List<String>> events){
        JPanel panel = new JPanel(new GridLayout(events.size()+1, 10, 10,5));
        panel.add(new JLabel("Title"));
        panel.add(new JLabel("ID"));
        panel.add(new JLabel("Start:"));
        panel.add(new JLabel("End"));
        panel.add(new JLabel("Duration: "));
        panel.add(new JLabel("Location: "));
        panel.add(new JLabel("VIP Status: "));
        panel.add(new JLabel("Capacity: "));
        panel.add(new JLabel("Speakers"));
        panel.add(new JLabel("Cancel"));
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
            cancelButtons[i] = new JButton("Really??!");
            cancelButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    presenter.cancelEvent(event.get(1));
                }
            });
            i++;
        }
        return panel;
    }

    public JPanel changeEvent(){
        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        JLabel eventID = new JLabel("Event ID: ");
        JTextField eventEntered = new JTextField();
        JLabel changeItem = new JLabel("You are changing: ");
        panel.add(changeItem);
        JComboBox changeItemSelected = new JComboBox(new String[]{"Speaker", "Capacity", "VIP Status"});
        panel.add(changeItemSelected);
        JLabel newSetting = new JLabel("New: ");
        panel.add(newSetting);
        JTextField newSettingEntered = new JTextField();
        panel.add(newSettingEntered);
        JButton resetButton = new JButton("Reset");
        JButton okButton = new JButton("OK");
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

    public void update(String action){}





}

