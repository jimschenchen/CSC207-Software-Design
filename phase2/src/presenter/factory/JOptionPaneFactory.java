package presenter.factory;

import presenter.language.Language;

import javax.swing.*;

public class JOptionPaneFactory {
    // This is for JOptionPane.showMessageDialog
    Language language;
    public JOptionPaneFactory(Language language){
        this.language = language;
    }
    public void errorMessage(){
        JOptionPane.showMessageDialog(null, language.fail(), language.messageTitle(),
                JOptionPane.ERROR_MESSAGE);
    }
    public void createAccountMessage(){
        JOptionPane.showMessageDialog(null, language.createAccountMessage(), language.messageTitle(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void succeedAddRoom(){
        JOptionPane.showMessageDialog(null, "New Room has been created successfully!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failAddRoom(){
        JOptionPane.showMessageDialog(null, "The room already exists or your input is valid",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    public void succeedCancelEvent(){
        JOptionPane.showMessageDialog(null, "Whoops, the event has been canceled successfully",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failCancelEvent(){
        JOptionPane.showMessageDialog(null, "It seems that you cannot cancel the event",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    public void succeedSignUpEvent(){
        JOptionPane.showMessageDialog(null, "You have signed up for this event!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failSignUpEvent(){
        JOptionPane.showMessageDialog(null, "It seems that you cannot sign up for this event",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    public void succeedWaitEvent(){
        JOptionPane.showMessageDialog(null, "You have signed up for the waiting" +
                        " list of this event!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failWaitEvent(){
        JOptionPane.showMessageDialog(null, "It seems that you cannot sign up for " +
                        "the waiting list of this event",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    public void succeedChangeEvent(){
        JOptionPane.showMessageDialog(null, "You have changed the setting of this event!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failChangeEvent(){
        JOptionPane.showMessageDialog(null, "It seems that you cannot change the setting  " +
                        "of this event",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }
    public void succeedCreateUser(){
        JOptionPane.showMessageDialog(null, "You have created a user!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public  void failCreateUser(){
        JOptionPane.showMessageDialog(null, "You fail to create a user!",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    public void succeedCreateEvent(){
        JOptionPane.showMessageDialog(null, "You have created an event!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failCreateEvent(){
        JOptionPane.showMessageDialog(null, "You fail to create an event!",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    public void succeedCancelEnrollment(){
        JOptionPane.showMessageDialog(null, "Whoops! You have cancel the enrollment from the " +
                        "event successfully!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failCancelEnrollment(){
        JOptionPane.showMessageDialog(null, "You fail to cancel the enrollment!",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    public void succeedRemoveWait(){
        JOptionPane.showMessageDialog(null, "Whoops! You have given up the waiting list " +
                        "spot for the event successfully",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public void failRemoveWait(){
        JOptionPane.showMessageDialog(null, "It seems that we cannot remove you from " +
                        "the waiting list of the event!",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }


}
