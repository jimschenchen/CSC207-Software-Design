/**
 * Base presenter which act as a Presenter in Model-View-Presenter
*/
import Controller.*;
public class Presenter {
    private ViewModel model;
    public Presenter(ViewModel model){
        this.model = model;
    }
    private void Introduction(){
        model.Introduction();
    }
    private void CredentialsHelper(){
        String username = model.Username();
        int pass = Integer.parseInt(model.Pass());
    }
    private void IsUserRegistered(){
        if (model.AreURegistered() == 1){
            CredentialsHelper();
            //here we should pass the info to the controller
        }
        else{
            model.SignIn();
        }
    }
    private void EverythingCorrect(boolean j, String username){
        //again should be taken from the controller
        if (j){
            model.Credentials(username);
        }
        else{
            model.CredentialsFail();
            CredentialsHelper();
        }
    }
    private void MenuOpener(){
        //controller should send information which menu to open
    }
    private void MessengerOpener(){
        model.Messenger();
        //controller should send information which messenger to open
    }
}
