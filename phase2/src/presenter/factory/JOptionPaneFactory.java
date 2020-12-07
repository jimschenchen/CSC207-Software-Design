package presenter.factory;

import presenter.language.Language;

import javax.swing.*;
/**
 *JOptionPaneFactory, as the name suggested, is used to make JOptionPane used in the program
 */
public class JOptionPaneFactory {
    Language language;
    /**
     * @param language the language used in the JOptionPane
     */
    public JOptionPaneFactory(Language language){
        this.language = language;
    }

    /**
     * get the JOptionPane wanted
     * Input "error" to tell there is an error
     * Input "createAccount" to tell the account has been made successfully
     * Input.......
     * @param instruction to tell the JOptionFactory which JOptionPane should it make
     */
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
        }else if(instruction == "succeedCreateEvent"){
            succeedCreateEvent();
        }else if(instruction == "failCreateEvent"){
            failCreateEvent();
        }
    }

    /**
     * @Description: tells you there is an error
     */
    private void error(){
        JOptionPane.showMessageDialog(null, language.fail(), language.messageTitle(),
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @Description: tells you an account has been created
     */
    private void createAccount(){
        JOptionPane.showMessageDialog(null, language.createAccountMessage(), language.messageTitle(),
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: a room has been successfully added
     */
    private void succeedAddRoom(){
        JOptionPane.showMessageDialog(null, language.succeedAddRoomMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: a room is failed to be added
     */
    private void failAddRoom(){
        JOptionPane.showMessageDialog(null, language.failAddRoomMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: an event has been successfully canceled
     */
    private void succeedCancelEvent(){
        JOptionPane.showMessageDialog(null, language.succeedCancelEventMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: a room hasn't been canceled
     */
    private void failCancelEvent(){
        JOptionPane.showMessageDialog(null, language.failCancelEventMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: an event has been successfully signed up
     */
    private void succeedSignUpEvent(){
        JOptionPane.showMessageDialog(null, language.succeedSignUpEventMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: an event is failed to signed up
     */
    private void failSignUpEvent(){
        JOptionPane.showMessageDialog(null, language.failSignUpEvent(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: an event has been successfully signed up
     */
    private void succeedWaitEvent(){
        JOptionPane.showMessageDialog(null, language.succeedWaitEvent(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: an event is failed to signed up
     */
    private void failWaitEvent(){
        JOptionPane.showMessageDialog(null, language.failWaitEventMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: an event has been successfully changed
     */
    private void succeedChangeEvent(){
        JOptionPane.showMessageDialog(null, language.succeedChangeEventMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: an event is failed to change
     */
    private void failChangeEvent(){
        JOptionPane.showMessageDialog(null, language.failChangeEventMessage(),
                language.successful(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: an user has been successfully created
     */
    private void succeedCreateUser(){
        JOptionPane.showMessageDialog(null, language.succeedCreateUserMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: an user is failed to be created
     */
    private  void failCreateUser(){
        JOptionPane.showMessageDialog(null, language.failCreateUserMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: an event has been successfully created
     */
    private void succeedCreateEvent(){
        JOptionPane.showMessageDialog(null, language.succeedCreateEventMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: an event is failed to be created
     */
    private void failCreateEvent(){
        JOptionPane.showMessageDialog(null, language.failCreateEventMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: the enrollment has been successfully canceled
     */
    private void succeedCancelEnrollment(){
        JOptionPane.showMessageDialog(null, language.succeedCancelEnrollmentMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: the enrollment is failed to be canceled
     */
    private void failCancelEnrollment(){
        JOptionPane.showMessageDialog(null, language.failCancelEnrollmentMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: an user has been successfully removed from the wait list
     */
    private void succeedRemoveWait(){
        JOptionPane.showMessageDialog(null, language.succeedRemoveWaitMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: an user is failed to be removed from the wait list
     */
    private void failRemoveWait(){
        JOptionPane.showMessageDialog(null, language.failRemoveWaitMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: successfully reset a pass
     */
    private void succeedResetPass(){
        JOptionPane.showMessageDialog(null, language.succeedResetPasswordMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: failed to reset a pass
     */
    private void failResetPass(){
        JOptionPane.showMessageDialog(null, language.failResetPasswordMessage(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description: an account has been successfully created
     */
    private void succeedCreateAccount(){
        JOptionPane.showMessageDialog(null, language.succeedCreateAccountMessage(),
                language.successful(), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @Description: an account is failed to be created
     */
    private void failCreateAccount(){
        JOptionPane.showMessageDialog(null, language.failCreateAccount(),
                language.failed(), JOptionPane.WARNING_MESSAGE);
    }



}
