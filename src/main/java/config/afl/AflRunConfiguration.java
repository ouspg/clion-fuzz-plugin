package config.afl;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
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

    //Extra tabs can be added through adding editors to the variable after inheriting this class
    public SettingsEditorGroup<AflRunConfiguration> tabbedEditorGroup;

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
