package config;


import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class BuildConfigurationTabComponent extends SettingsEditor<FuzzerRunConfiguration> {
    private JPanel rootPanel;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private EnvironmentSettingsUIComponent environmentSettingsPanel;
    private JPanel fuzzerSpecificPanel;

    @Override
    protected void resetEditorFrom(@NotNull FuzzerRunConfiguration s) {
        environmentSettingsPanel.setProgramArgumentsField(s.getOptions().getBuildProgramArguments());
        environmentSettingsPanel.setWorkingDirectoryField(s.getOptions().getBuildWorkingDirectory());
        environmentSettingsPanel.setEnvironmentVariablesField(s.getOptions().getBuildEnvironmentVariables());
    }

    @Override
    protected void applyEditorTo(@NotNull FuzzerRunConfiguration s) {
        s.getOptions().setBuildProgramArguments(environmentSettingsPanel.getProgramArgumentsField());
        s.getOptions().setBuildWorkingDirectory(environmentSettingsPanel.getWorkingDirectoryField());
        s.getOptions().setBuildEnvironmentVariables(environmentSettingsPanel.getEnvironmentVariablesField());
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return rootPanel;
    }

    public BuildConfigurationTabComponent(){
    }

    public BuildConfigurationTabComponent(JPanel fuzzerSpecificBuildPanel){
        this.fuzzerSpecificPanel = fuzzerSpecificBuildPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
