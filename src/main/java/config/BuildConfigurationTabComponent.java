package config;


import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class BuildConfigurationTabComponent<Settings extends FuzzerRunConfiguration> extends FuzzerConfigTabComponent<Settings> {
    private JPanel rootPanel;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private EnvironmentSettingsUIComponent environmentSettingsPanel;
    private JPanel fuzzerSpecificPanel;


    public BuildConfigurationTabComponent(){
        createEditor(fuzzerSpecificPanel, rootPanel);
    }

    @Override
    public void resetEditorFrom(@NotNull Settings s) {
        environmentSettingsPanel.setProgramArgumentsField(s.getOptions().getBuildProgramArguments());
        environmentSettingsPanel.setWorkingDirectoryField(s.getOptions().getBuildWorkingDirectory());
        environmentSettingsPanel.setEnvironmentVariablesField(s.getOptions().getBuildEnvironmentVariables());
    }

    @Override
    public void applyEditorTo(@NotNull Settings s) {
        s.getOptions().setBuildProgramArguments(environmentSettingsPanel.getProgramArgumentsField());
        s.getOptions().setBuildWorkingDirectory(environmentSettingsPanel.getWorkingDirectoryField());
        s.getOptions().setBuildEnvironmentVariables(environmentSettingsPanel.getEnvironmentVariablesField());
    }

    @Override
    @NotNull
    public JComponent createEditor() {
        return rootPanel;
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
