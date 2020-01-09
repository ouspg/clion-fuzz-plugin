package config;

import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


//This is the starting point of the configuration for plugin
//Initialisation of the configuration type is done through this class
public final class FuzzerRunConfigurationType extends ConfigurationTypeBase {
    private static final String ID = "fuzzer.run.config.type";
    @NotNull
    public static FuzzerRunConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(FuzzerRunConfigurationType.class);
    }

    public FuzzerRunConfigurationType() {
        //TODO add an Icon here
        super(ID, "Fuzzer", "Description", AllIcons.General.Error);
        addFactory(new FuzzerRunConfigurationFactory(this));
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
