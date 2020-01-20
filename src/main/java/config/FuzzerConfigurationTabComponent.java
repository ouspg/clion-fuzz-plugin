package config;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FuzzerConfigurationTabComponent<Settings extends FuzzerRunConfiguration> extends SettingsEditorGroup<Settings> {
    private JPanel rootPanel;
    private SettingsEditor<Settings> fuzzerSpecificSettingsEditor;
    private JPanel fuzzerSpecificPanel;
    private EnvironmentSettingsUIComponent environmentSettingsPanel;


    @Override
    public void resetEditorFrom(@NotNull Settings s) {
        environmentSettingsPanel.setProgramArgumentsField(s.getOptions().getFuzzerProgramArguments());
        environmentSettingsPanel.setWorkingDirectoryField(s.getOptions().getFuzzerWorkingDirectory());
        environmentSettingsPanel.setEnvironmentVariablesField(s.getOptions().getFuzzerEnvironmentVariables());
    }

    @Override
    public void applyEditorTo(@NotNull Settings s)  {
        s.getOptions().setFuzzerProgramArguments(environmentSettingsPanel.getProgramArgumentsField());
        s.getOptions().setFuzzerWorkingDirectory(environmentSettingsPanel.getWorkingDirectoryField());
        s.getOptions().setFuzzerEnvironmentVariables(environmentSettingsPanel.getEnvironmentVariablesField());
    }

    @Override
    public  @NotNull JComponent createEditor() {
        return rootPanel;
    }

    private void createUIComponents() {
    }
}
