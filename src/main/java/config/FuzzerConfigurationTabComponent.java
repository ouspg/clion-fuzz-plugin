package config;

import com.intellij.execution.configurations.RunConfiguration;

import javax.swing.*;
import java.util.Set;

public class FuzzerConfigurationTabComponent {
    private JPanel rootPanel;
    private JPanel fuzzerSpecificOptionsPanel;

    FuzzerConfigurationTabComponent() {
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
