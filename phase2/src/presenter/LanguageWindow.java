package presenter;

import javax.swing.*;
import java.awt.*;

public class LanguageWindow extends JFrame implements IWindow{
    ButtonGroup buttonGroup;
    JRadioButton simplifiedChineseButton;
    JRadioButton englishButton;
    JRadioButton russiaButton;
    JRadioButton traditionalChineseButton;
    JRadioButton japaneseButton;
    JButton okButton;
    JButton backButton;
    Language language;

    Font defaultFont = new Font("Mononspace", 1, 25);

    public LanguageWindow(Language language){
        this.language = language;
        init();


    }
    private void init(){
        setLayout(null);
        buttonGroup = new ButtonGroup();

        simplifiedChineseButton = new JRadioButton(new SimplifiedChinese().language());
        simplifiedChineseButton.setBounds(200,20,300,70);
        simplifiedChineseButton.setFont(defaultFont);

        englishButton = new JRadioButton(new English().language());
        englishButton.setSelected(true);
        englishButton.setBounds(200,100,300,70);
        englishButton.setFont(defaultFont);

        russiaButton = new JRadioButton("Not Yet");
        russiaButton.setBounds(200,180, 300,70);
        russiaButton.setFont(defaultFont);

        traditionalChineseButton = new JRadioButton("Not Yet");
        traditionalChineseButton.setBounds(200,260,300,70);
        traditionalChineseButton.setFont(defaultFont);

        japaneseButton = new JRadioButton("Not yet");
        japaneseButton.setBounds(200, 340, 300,70);
        japaneseButton.setFont(defaultFont);

        buttonGroup.add(englishButton);
        buttonGroup.add(simplifiedChineseButton);
        buttonGroup.add(russiaButton);
        buttonGroup.add(traditionalChineseButton);
        buttonGroup.add(japaneseButton);

        add(englishButton);
        add(simplifiedChineseButton);
        add(russiaButton);
        add(traditionalChineseButton);
        add(japaneseButton);

        okButton = new JButton(language.ok());
        okButton.setBounds(100, 420, 100,50);
        okButton.setFont(defaultFont);
        add(okButton);

        backButton = new JButton(language.back());
        backButton.setBounds(350, 420, 100,50);
        backButton.setFont(defaultFont);
        add(backButton);

        setTitle(language.selectLanguage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocation(450,200);
        setVisible(true);

    }


}
