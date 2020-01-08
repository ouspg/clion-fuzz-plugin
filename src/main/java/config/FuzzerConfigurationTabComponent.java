package config;

import com.intellij.execution.configurations.RunConfiguration;

import javax.swing.*;
import java.util.Set;

public class FuzzerConfigurationTabComponent extends BaseConfigurationTabPanel{
    private JPanel rootPanel;
    private JPanel fuzzerSpecificOptionsPanel;

    /**
     * No modifier means it's package-protected
     *
     * @param defaultRunConfigurations
     * @param fuzzerSpecificPanel
     */
    FuzzerConfigurationTabComponent(Set<RunConfiguration> defaultRunConfigurations, FuzzerRunConfigurableUIPanel fuzzerSpecificPanel) {
        super(defaultRunConfigurations, fuzzerSpecificPanel);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void setAllPopulatableComponents(Set<PopulatableUIComponent<?>> populatableUIComponents) {

    }

    @Override
    public void clearAllPopulatableComponents() {

    }

    @Override
    public void setDefaultRunConfiguration(Set<RunConfiguration> runConfigurations) {

    }
}
