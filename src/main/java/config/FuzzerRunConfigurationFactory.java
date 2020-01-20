package config;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public abstract class FuzzerRunConfigurationFactory extends ConfigurationFactory {

    public FuzzerRunConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
        System.out.println("FuzzerConfiguration Factory being initiated");
    }

    @Override
    public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return null;
    }

    /**
     * Override this class with the Name of the fuzzer you want to give
     * @return String - name of the fuzzer
     */
    @NotNull
    @Override
    public String getName(){
        return "Fuzzer Run/Debug configuration factory";
    }

    /**
     * Override this class with the options class of your fuzzer
     * @return Options class of your fuzzer for e.g. AflFuzzerConfigurationOptions.class
     */
    @NotNull
    @Override
    public Class<? extends BaseState> getOptionsClass(){
        return FuzzerRunConfigurationOptions.class;
    }
}
