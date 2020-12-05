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


}
