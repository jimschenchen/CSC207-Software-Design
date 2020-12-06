package presenter.factory;

import presenter.language.Language;

import javax.swing.*;

public class JOptionPaneFactory {
    // This is for JOptionPane.showMessageDialog
    Language language;
    public JOptionPaneFactory(Language language){
        this.language = language;
    }
    public void get(String instruction){
        if(instruction == "error"){
            error();
        }else if(instruction == "createAccount"){
            createAccount();
        }else if(instruction == "succeedAddRoom"){
            succeedAddRoom();
        }else if(instruction == "failAddRoom"){
            failAddRoom();
        }else if(instruction == "succeedCancelEvent"){
            succeedCancelEvent();
        }else if(instruction == "failCancelEvent"){
            failCancelEvent();
        }else if(instruction == "succeedSignUpEvent"){
            succeedSignUpEvent();
        }else if(instruction == "failSignUpEvent"){
            failSignUpEvent();
        }else if(instruction == "succeedWaitEvent"){
            succeedWaitEvent();
        }else if(instruction == "failWaitEvent"){
            failWaitEvent();
        }else if(instruction == "succeedChangeEvent"){
            succeedCancelEvent();
        }else if(instruction == "failChangeEvent"){
            failCancelEvent();
        }else if(instruction == "succeedCreateUser"){
            succeedCreateUser();
        }else if (instruction == "failCreateUser"){
            failCreateUser();
        }else if(instruction =="succeedCreateEvent"){
            succeedCreateEvent();
        }else if(instruction == "failCreateEvent"){
            failCreateEvent();
        }else if(instruction == "succeedCancelEnrollment"){
            succeedCancelEnrollment();
        }else if(instruction == "failCancelEnrollment"){
            failCancelEnrollment();
        }else if(instruction == "succeedRemoveWait"){
            succeedRemoveWait();
        }else if(instruction == "failRemoveWait"){
            failRemoveWait();
        }
    }

    private void error(){
        JOptionPane.showMessageDialog(null, language.fail(), language.messageTitle(),
                JOptionPane.ERROR_MESSAGE);
    }
    private void createAccount(){
        JOptionPane.showMessageDialog(null, language.createAccountMessage(), language.messageTitle(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void succeedAddRoom(){
        JOptionPane.showMessageDialog(null, "New Room has been created successfully!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void failAddRoom(){
        JOptionPane.showMessageDialog(null, "The room already exists or your input is valid",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    private void succeedCancelEvent(){
        JOptionPane.showMessageDialog(null, "Whoops, the event has been canceled successfully",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void failCancelEvent(){
        JOptionPane.showMessageDialog(null, "It seems that you cannot cancel the event",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    private void succeedSignUpEvent(){
        JOptionPane.showMessageDialog(null, "You have signed up for this event!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void failSignUpEvent(){
        JOptionPane.showMessageDialog(null, "It seems that you cannot sign up for this event",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    private void succeedWaitEvent(){
        JOptionPane.showMessageDialog(null, "You have signed up for the waiting" +
                        " list of this event!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void failWaitEvent(){
        JOptionPane.showMessageDialog(null, "It seems that you cannot sign up for " +
                        "the waiting list of this event",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    private void succeedChangeEvent(){
        JOptionPane.showMessageDialog(null, "You have changed the setting of this event!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void failChangeEvent(){
        JOptionPane.showMessageDialog(null, "It seems that you cannot change the setting  " +
                        "of this event",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }
    private void succeedCreateUser(){
        JOptionPane.showMessageDialog(null, "You have created a user!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private  void failCreateUser(){
        JOptionPane.showMessageDialog(null, "You fail to create a user!",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    private void succeedCreateEvent(){
        JOptionPane.showMessageDialog(null, "You have created an event!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void failCreateEvent(){
        JOptionPane.showMessageDialog(null, "You fail to create an event!",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    private void succeedCancelEnrollment(){
        JOptionPane.showMessageDialog(null, "Whoops! You have cancel the enrollment from the " +
                        "event successfully!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void failCancelEnrollment(){
        JOptionPane.showMessageDialog(null, "You fail to cancel the enrollment!",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }

    private void succeedRemoveWait(){
        JOptionPane.showMessageDialog(null, "Whoops! You have given up the waiting list " +
                        "spot for the event successfully",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    private void failRemoveWait(){
        JOptionPane.showMessageDialog(null, "It seems that we cannot remove you from " +
                        "the waiting list of the event!",
                "Failed", JOptionPane.WARNING_MESSAGE);
    }



}
