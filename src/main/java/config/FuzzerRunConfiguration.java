package config;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.PathUtil;

import java.nio.file.Path;

public class FuzzerRunConfiguration extends RunConfigurationBase<FuzzerRunConfigurationOptions> {
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
    public @NotNull SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        SettingsEditorGroup<FuzzerRunConfiguration> tabbedEditorGroup = new SettingsEditorGroup<>();
        tabbedEditorGroup.addEditor("Fuzzer", new FuzzerConfigurationTabComponent());
        tabbedEditorGroup.addEditor("Build", new BuildConfigurationTabComponent());
        tabbedEditorGroup.addEditor("Code Coverage", new CodeCoverageTabComponent());
        return tabbedEditorGroup;
    }

    @Override
    public @Nullable RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment)
            throws ExecutionException {
        return null;
    }

}
