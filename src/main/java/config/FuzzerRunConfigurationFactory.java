package config;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import fuzzer.AflFuzzer;
import org.jetbrains.annotations.NotNull;

public class FuzzerRunConfigurationFactory extends ConfigurationFactory {

    public FuzzerRunConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
        System.out.println("FuzzerConfiguration Factory being initiated");
    }

    @Override
    public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new FuzzerRunConfiguration(project, this, "FirstTestConfig");
    }

    @NotNull
    @Override
    public String getName(){
        return "Fuzzer Run/Debug configuration factory";
    }

    @NotNull
    @Override
    public Class<? extends BaseState> getOptionsClass(){
        return FuzzerRunConfigurationOptions.class;
    }
}
