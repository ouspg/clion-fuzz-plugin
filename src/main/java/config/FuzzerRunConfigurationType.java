package config;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


//This is the starting point of the configuration for plugin
//Initialisation of the configuration type is done through this class
public final class FuzzerRunConfigurationType extends ConfigurationTypeBase {
    private static final String ID = "fuzzer.run.config.type";
    public FuzzerRunConfigurationType() {
        //TODO add an Icon here
        super(ID, "Fuzzer", "Description", AllIcons.General.Error);
    }

    @Override
    public @NotNull String getDisplayName() {
        return "Fuzzer";
    }

    //TODO Create an Icon for the plugin
    @Override
    public Icon getIcon() {
        return null;
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
