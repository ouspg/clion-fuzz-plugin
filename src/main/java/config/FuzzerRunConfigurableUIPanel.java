package config;

import com.intellij.execution.configurations.RunConfiguration;

import javax.swing.*;
import java.util.Set;

public interface FuzzerRunConfigurableUIPanel {
    JPanel getRootPanel();
    void setAllPopulatableComponents(Set<PopulatableUIComponent<?>> populatableUIComponents);
    void clearAllPopulatableComponents();
    void setConfigOptions(Set<RunConfiguration> runConfigurations);
    void resetConfigOptionsToDefault();
    void setDefaultRunConfiguration(Set<RunConfiguration> runConfigurations);
}
