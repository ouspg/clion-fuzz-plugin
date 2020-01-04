package config;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


//This is the starting point of the configuration for plugin
//Initialisation of the configuration type is done through this class
public class FuzzerRunConfigurationType implements ConfigurationType {
    @Override
    public @NotNull String getDisplayName() {
        return "Fuzzer";
    }

    @Nls
    @Override
    public String getConfigurationTypeDescription() {
        return "Fuzzer Run/Debug Configuration";
    }

    //TODO Create an Icon for the plugin
    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public @NotNull String getId() {
        return "fuzzer.run.configuration.default";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        System.out.println("Getting configuration factories");
        return new ConfigurationFactory[]{new FuzzerRunConfigurationFactory(this)};
    }

    //TODO write help documentation for the Run/debug configurations
    @Override
    public @Nullable String getHelpTopic() {
        return null;
    }
}
