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
}
