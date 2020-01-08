package config;

import javax.swing.*;
import java.awt.*;

public class CodeCoverageTabComponent{
    private JPanel rootPanel;
    private JCheckBox enableCodeCoverageForCheckBox;
    private JPanel fuzzerSpecificCodeCoverageOptions;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JPanel setFuzzerSpecificPanel() {
        return null;
    }
}
