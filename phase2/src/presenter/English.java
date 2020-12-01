package presenter;

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
}
