package config.afl;

import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import config.FuzzerRunConfiguration;
import config.FuzzerRunConfigurationFactory;
import config.FuzzerRunConfigurationOptions;
import org.jetbrains.annotations.NotNull;

public class AflRunConfigurationFactory extends FuzzerRunConfigurationFactory {
    public AflRunConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }


    @Override
    public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new AflRunConfiguration(project, this, "FirstTestConfig");
    }

    @NotNull
    @Override
    public String getName(){
        return "AFL";
    }

    @NotNull
    @Override
    public Class<? extends BaseState> getOptionsClass(){
        return AflRunConfigurationOptions.class;
    }
}

