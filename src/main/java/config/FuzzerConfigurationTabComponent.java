package config;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class FuzzerConfigurationTabComponent<Settings extends FuzzerRunConfiguration> extends FuzzerConfigTabComponent<Settings> {
    private JPanel rootPanel;
    private JPanel fuzzerSpecificPanel;
    private EnvironmentSettingsUIComponent environmentSettingsPanel;
    public FuzzerConfigurationTabComponent(){
        createEditor(fuzzerSpecificPanel, rootPanel);
    }

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

    private void createUIComponents() {
    }
}
