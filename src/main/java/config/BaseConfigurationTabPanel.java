package config;

import com.intellij.execution.configurations.RunConfiguration;

import javax.swing.*;
import java.util.Set;

public abstract class BaseConfigurationTabPanel implements FuzzerRunConfigurableUIPanel {
    private JPanel rootPanel;
    private final FuzzerRunConfigurableUIPanel fuzzerSpecificPanel;
    private final Set<RunConfiguration> defaultRunConfigurations;
    private Set<RunConfiguration> currentRunConfigurations;
    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * No modifier means it's package-protected
     * @param defaultRunConfigurations
     */
    BaseConfigurationTabPanel(Set<RunConfiguration> defaultRunConfigurations, FuzzerRunConfigurableUIPanel fuzzerSpecificPanel){
        this.defaultRunConfigurations = defaultRunConfigurations;
        this.fuzzerSpecificPanel = fuzzerSpecificPanel;
    }

    @Override
    public void setConfigOptions(Set<RunConfiguration> runConfigurations) {
        this.currentRunConfigurations = runConfigurations;
    }

    @Override
    public void resetConfigOptionsToDefault() {
        this.currentRunConfigurations = defaultRunConfigurations;
    }
}
