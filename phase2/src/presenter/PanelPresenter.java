package presenter;

import controller.ConferenceSystem;

public class PanelPresenter {
    ConferenceSystem cs;
    IUpdate panel;
    public PanelPresenter(ConferenceSystem cs, IUpdate panel){
        this.cs = cs;
        this.panel = panel;
    }
    public void signUpEvent(String eventID){
        Boolean success = cs.signUpForEvent(eventID);
        if(success){
            panel.update("succeedSignUpEvent");
        }else{
            panel.update("failSignUpEvent");
        }
    }

    public  void waitEvent(String eventID){
        Boolean success = cs.signUpForEventWaitList(eventID);
        if(success){
            panel.update("succeedWaitEvent");
        }else{
            panel.update("failWaitEvent");
        }
    }

    public void cancelEvent(String eventID){
        Boolean success = cs.cancelEvent(eventID);
        if(success){
            panel.update("succeedCancelEvent");
        }else{
            panel.update("failCancelEvent");
        }
    }

    public void changeEvent(String changeItem, String changeItemEntered){
        Boolean success;
        if (changeItem == "Speaker"){
            success = cs.modifySpeakerForEvent(changeItemEntered)
        }
    }

}
