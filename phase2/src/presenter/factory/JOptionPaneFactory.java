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


}
