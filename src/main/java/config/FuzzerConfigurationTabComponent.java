package config;

import javax.swing.*;

public class FuzzerConfigurationTabComponent extends JComponent {
    private JPanel rootPanel;
    private JPanel fuzzerSpecificOptionsPanel;

    public FuzzerConfigurationTabComponent(){

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
