package config.afl;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.project.Project;
import config.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class AflRunConfiguration extends FuzzerRunConfiguration {
    protected AflRunConfiguration(@NotNull Project project, @Nullable ConfigurationFactory factory, @Nullable String name) {
        super(project, factory, name);
    }

    @Override
    public @NotNull SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        tabbedEditorGroup = new SettingsEditorGroup<>();
        tabbedEditorGroup.addEditor("Fuzzer", new AflFuzzerConfigurationTabComponent<>());
        //When populating components of Build it should send the options when creating the configuration
        tabbedEditorGroup.addEditor("Build", new AflBuildConfigurationTabComponent<>());
        tabbedEditorGroup.addEditor("Code Coverage", new AflCodeCoverageTabComponent<>());
        return tabbedEditorGroup;
    }

    @NotNull
    @Override
    public AflRunConfigurationOptions getOptions(){
        return (AflRunConfigurationOptions) super.getOptions();
    }
}
