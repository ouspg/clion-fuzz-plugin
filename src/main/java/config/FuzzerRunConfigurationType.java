package config;

import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//TODO write description
//This is the starting point of the configuration for plugin
//Initialisation of the configuration type is done through this class
public final class FuzzerRunConfigurationType extends ConfigurationTypeBase implements ConfigurationType {
    private static final String ID = "fuzzer.run.config.type";
    
    public FuzzerRunConfigurationType() {
        //TODO add an Icon here
        super(ID, "Fuzzer", "Description", AllIcons.General.Error);
        addFactory(new FuzzerRunConfigurationFactory(this));
        //Future fuzzers should be added here!!!
        //addFactory(new AflFuzzerRunConfigurationFactory(this));
    }

    @Override
    public @NotNull String getDisplayName() {
        return "Fuzzer";
    }

    //TODO write help documentation for the Run/debug configurations
    @Override
    public @Nullable String getHelpTopic() {
        return null;
    }
}
