package config;

import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.fields.ExpandableTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Set;

public class FuzzerConfigurationTabComponent extends SettingsEditor<FuzzerRunConfiguration> {
    private JPanel rootPanel;
    private JPanel fuzzerSpecificOptionsPanel;
    private EnvironmentSettingsUIComponent environmentSettingsPanel;


    @Override
    protected void resetEditorFrom(@NotNull FuzzerRunConfiguration s) {
        environmentSettingsPanel.setProgramArgumentsField(s.getOptions().getFuzzerProgramArguments());
        environmentSettingsPanel.setWorkingDirectoryField(s.getOptions().getFuzzerWorkingDirectory());
        environmentSettingsPanel.setEnvironmentVariablesField(s.getOptions().getFuzzerEnvironmentVariables());
    }

    @Override
    protected void applyEditorTo(@NotNull FuzzerRunConfiguration s) throws ConfigurationException {
        s.getOptions().setFuzzerProgramArguments(environmentSettingsPanel.getProgramArgumentsField());
        s.getOptions().setFuzzerWorkingDirectory(environmentSettingsPanel.getWorkingDirectoryField());
        s.getOptions().setFuzzerEnvironmentVariables(environmentSettingsPanel.getEnvironmentVariablesField());
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return rootPanel;
    }
}
