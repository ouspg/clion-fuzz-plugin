package config;

import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.project.Project;
import config.afl.AflFuzzConfigPanel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FuzzerRunConfiguration extends RunConfigurationBase<FuzzerRunConfigurationOptions> {
    protected FuzzerRunConfiguration(@NotNull Project project, @Nullable ConfigurationFactory factory,
                                     @Nullable String name) {
        super(project, factory, name);
    }

    //Extra tabs can be added through adding editors to the variable after inheriting this class
    protected SettingsEditorGroup<FuzzerRunConfiguration> tabbedEditorGroup;

    @Override
    public @NotNull SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        tabbedEditorGroup = new SettingsEditorGroup<>();
        AflFuzzConfigPanel specialPanel = new AflFuzzConfigPanel();
        tabbedEditorGroup.addEditor("Fuzzer", new FuzzerConfigurationTabComponent());
        //When populating components of Build it should send the options when creating the configuration
        tabbedEditorGroup.addEditor("Build", new BuildConfigurationTabComponent());
        tabbedEditorGroup.addEditor("Code Coverage", new CodeCoverageTabComponent());
        return tabbedEditorGroup;
    }

    @NotNull
    @Override
    public FuzzerRunConfigurationOptions getOptions(){
        return (FuzzerRunConfigurationOptions) super.getOptions();
    }


    @Override
    public @Nullable RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) {
        return null;
    }
}
