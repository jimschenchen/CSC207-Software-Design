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
            succeedChangeEvent();
        }else if(instruction == "failChangeEvent"){
            failChangeEvent();
        }else if(instruction == "succeedCreateUser"){
            succeedCreateUser();
        }else if (instruction == "failCreateUser"){
            failCreateUser();
        }else if(instruction == "succeedCancelEnrollment"){
            succeedCancelEnrollment();
        }else if(instruction == "failCancelEnrollment"){
            failCancelEnrollment();
        }else if(instruction == "succeedRemoveWait"){
            succeedRemoveWait();
        }else if(instruction == "failRemoveWait"){
            failRemoveWait();
        }else if(instruction == "succeedResetPass"){
            succeedResetPass();
        }else if(instruction == "failResetPass"){
            failResetPass();
        }else if(instruction == "succeedCreateAccount"){
            succeedCreateAccount();
        }else if(instruction == "failCreateAccount"){
            failCreateAccount();
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
        JOptionPane.showMessageDialog(null, language.succeedAddRoomMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failAddRoom(){
        JOptionPane.showMessageDialog(null, language.failAddRoomMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedCancelEvent(){
        JOptionPane.showMessageDialog(null, language.succeedCancelEventMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failCancelEvent(){
        JOptionPane.showMessageDialog(null, language.failCancelEventMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedSignUpEvent(){
        JOptionPane.showMessageDialog(null, language.succeedSignUpEventMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failSignUpEvent(){
        JOptionPane.showMessageDialog(null, language.failSignUpEvent(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedWaitEvent(){
        JOptionPane.showMessageDialog(null, language.succeedWaitEvent(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failWaitEvent(){
        JOptionPane.showMessageDialog(null, language.failWaitEventMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedChangeEvent(){
        JOptionPane.showMessageDialog(null, language.succeedChangeEventMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failChangeEvent(){
        JOptionPane.showMessageDialog(null, language.failChangeEventMessage(),
                language.successful(), JOptionPane.WARNING_MESSAGE);
    }
    private void succeedCreateUser(){
        JOptionPane.showMessageDialog(null, language.succeedCreateUserMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private  void failCreateUser(){
        JOptionPane.showMessageDialog(null, language.failCreateUserMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedCreateEvent(){
        JOptionPane.showMessageDialog(null, language.succeedCreateEventMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failCreateEvent(){
        JOptionPane.showMessageDialog(null, language.failCreateEventMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedCancelEnrollment(){
        JOptionPane.showMessageDialog(null, language.succeedCancelEnrollmentMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failCancelEnrollment(){
        JOptionPane.showMessageDialog(null, language.failCancelEnrollmentMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedRemoveWait(){
        JOptionPane.showMessageDialog(null, language.succeedRemoveWaitMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failRemoveWait(){
        JOptionPane.showMessageDialog(null, language.failRemoveWaitMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedResetPass(){
        JOptionPane.showMessageDialog(null, language.succeedResetPasswordMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failResetPass(){
        JOptionPane.showMessageDialog(null, language.failResetPasswordMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    private void succeedCreateAccount(){
        JOptionPane.showMessageDialog(null, language.succeedCreateAccountMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void failCreateAccount(){
        JOptionPane.showMessageDialog(null, language.failCreateAccount(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }



}
