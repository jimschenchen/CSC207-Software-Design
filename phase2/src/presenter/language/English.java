package presenter.language;

public class English implements Language {
    @Override
    public String username(){
        return ("Username:");
    };

    @Override
    public String password(){
        return ("Password: ");
    };

    @Override
    public String signUpAccount(){
        return ("Sign up for free now!");
    };

    @Override
    public String ok(){
        return ("OK");
    };

    @Override
    public String exit(){
        return ("Exit");
    };

    @Override
    public String titleLogin(){
        return ("Welcome to the Best Conference in the World! ");
    };

    @Override
    public String back() {
        return ("Back");
    }

    @Override
    public String titleSignUp() {
        return ("You are signing up!");
    }

    @Override
    public String language(){
        return ("English");
    }

    @Override
    public String selectLanguage(){
        return("Select your language! ");
    }

    @Override
    public String fail() {
        return ("Oops. It looks like something went wrong! Try again!");
    }

    @Override
    public String messageTitle() {
        return ("Message");
    }

    @Override
    public String createAccountMessage() {
        return ("Hey! You have created an account!");
    }

    @Override
    public String succeedAddRoomMessage() {
        return "New Room has been created successfully!";
    }

    @Override
    public String successful() {
        return "Successful";
    }

    @Override
    public String failed() {
        return "Failed";
    }

    @Override
    public String failAddRoomMessage() {
        return "The room already exists or your input is valid";
    }

    @Override
    public String succeedCancelEventMessage() {
        return "Whoops, the event has been canceled successfully";
    }

    @Override
    public String failCancelEventMessage() {
        return "It seems that you cannot cancel the event";
    }

    @Override
    public String succeedSignUpEventMessage() {
        return "You have signed up for this event!";
    }

    @Override
    public String failSignUpEvent() {
        return "It seems that you cannot sign up for this event";
    }

    @Override
    public String succeedWaitEvent() {
        return "You have signed up for the waiting";
    }

    @Override
    public String failWaitEventMessage() {
        return "You have signed up for the waiting list of this event!";
    }

    @Override
    public String succeedChangeEventMessage() {
        return "You have changed the setting of this event!";
    }

    @Override
    public String failChangeEventMessage() {
        return "It seems that you cannot change the setting of this event";
    }

    @Override
    public String succeedCreateUserMessage() {
        return "You have created a user!";
    }

    @Override
    public String failCreateUserMessage() {
        return "You fail to create a user!";
    }

    @Override
    public String succeedCreateEventMessage() {
        return "You have created an event!";
    }

    @Override
    public String failCreateEventMessage() {
        return "You fail to create an event!";
    }

    @Override
    public String succeedCancelEnrollmentMessage() {
        return "Whoops! You have cancel the enrollment from the event successfully!";
    }

    @Override
    public String failCancelEnrollmentMessage() {
        return "You fail to cancel the enrollment!";
    }

    @Override
    public String succeedRemoveWaitMessage() {
        return "Whoops! You have given up the waiting list spot for the event successfully";
    }

    @Override
    public String failRemoveWaitMessage() {
        return "It seems that we cannot remove you from the waiting list of the event!";
    }

    @Override
    public String succeedResetPasswordMessage() {
        return "You have a new password!";
    }

    @Override
    public String failResetPasswordMessage() {
        return "You cannot reset your new password to this password!";
    }

    @Override
    public String succeedCreateAccountMessage() {
        return "You have created an account!";
    }

    @Override
    public String failCreateAccount() {
        return "It seems that you cannot create an account";
    }

}
