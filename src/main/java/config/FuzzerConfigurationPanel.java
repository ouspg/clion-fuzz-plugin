package config;

import com.intellij.execution.impl.ConfigurationSettingsEditor;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.cpp.cmake.model.CMakeModelConfigurationData;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfigurationSettingsEditor;
import settings.CMakeConfigurationSetup;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FuzzerConfigurationPanel extends JPanel {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;

    public FuzzerConfigurationPanel(){
        tabbedPane.setComponentAt(0, new FuzzerConfigurationTabComponent().getRootPanel());
    }
    public JPanel getRootPanel(){
        return rootPanel;
    }
}
