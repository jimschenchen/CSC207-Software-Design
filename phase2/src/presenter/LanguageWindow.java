package presenter;

import presenter.language.*;

import javax.swing.*;
import java.awt.*;

public class LanguageWindow extends JFrame {
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

    public LanguageWindow(Language language) {
        this.language = language;
        init();


    }

    private void init() {
        JPanel languagePanel = new JPanel();
        setContentPane(languagePanel);
        languagePanel.setLayout(new GridLayout(4,2,0,50));

        buttonGroup = new ButtonGroup();

        simplifiedChineseButton = new JRadioButton(new SimplifiedChinese().language());
        simplifiedChineseButton.setFont(defaultFont);
        simplifiedChineseButton.setFont(defaultFont);
        simplifiedChineseButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        englishButton = new JRadioButton(new English().language());
        englishButton.setSelected(true);
        englishButton.setFont(defaultFont);

        russiaButton = new JRadioButton("Not Yet");
        russiaButton.setFont(defaultFont);

        traditionalChineseButton = new JRadioButton("Not Yet");
        traditionalChineseButton.setFont(defaultFont);

        japaneseButton = new JRadioButton("Not Yet");
        japaneseButton.setFont(defaultFont);


        buttonGroup.add(englishButton);
        buttonGroup.add(simplifiedChineseButton);
        buttonGroup.add(russiaButton);
        buttonGroup.add(traditionalChineseButton);
        buttonGroup.add(japaneseButton);

        languagePanel.add(englishButton);
        languagePanel.add(simplifiedChineseButton);
        languagePanel.add(russiaButton);
        languagePanel.add(traditionalChineseButton);
        languagePanel.add(japaneseButton);

        JLabel empty = new JLabel("More is coming!");
        empty.setFont(defaultFont);
        languagePanel.add(empty);


        okButton = new JButton(language.ok());
        okButton.setFont(defaultFont);
        languagePanel.add(okButton);

        backButton = new JButton(language.back());
        backButton.setFont(defaultFont);
        languagePanel.add(backButton);




        setTitle(language.selectLanguage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocation(450, 200);
        setVisible(true);


    }
}
