package config;

import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.project.Project;
import config.afl.AflRunConfiguration;
import fuzzer.FuzzerRunProfileState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FuzzerRunConfiguration extends RunConfigurationBase<FuzzerRunConfigurationOptions> {
    protected FuzzerRunConfiguration(@NotNull Project project, @Nullable ConfigurationFactory factory,
                                     @Nullable String name) {
        super(project, factory, name);
    }

    @NotNull
    @Override
    public FuzzerRunConfigurationOptions getOptions(){
        return (FuzzerRunConfigurationOptions) super.getOptions();
    }


    @Override
    public @Nullable RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) {
        return new FuzzerRunProfileState(environment);
    }
}
