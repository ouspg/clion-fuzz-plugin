package config;

import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.fields.ExpandableTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class BuildConfigurationTabComponent extends SettingsEditor<FuzzerRunConfiguration> {
    private JPanel rootPanel;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private EnvironmentSettingsUIComponent environmentSettingsPanel;

    @Override
    protected void resetEditorFrom(@NotNull FuzzerRunConfiguration s) {
        environmentSettingsPanel.setProgramArgumentsField(s.getOptions().getBuildProgramArguments());
        environmentSettingsPanel.setWorkingDirectoryField(s.getOptions().getBuildWorkingDirectory());
        environmentSettingsPanel.setEnvironmentVariablesField(s.getOptions().getBuildEnvironmentVariables());
    }

    @Override
    protected void applyEditorTo(@NotNull FuzzerRunConfiguration s) throws ConfigurationException {
        s.getOptions().setBuildProgramArguments(environmentSettingsPanel.getProgramArgumentsField());
        s.getOptions().setBuildWorkingDirectory(environmentSettingsPanel.getWorkingDirectoryField());
        s.getOptions().setBuildEnvironmentVariables(environmentSettingsPanel.getEnvironmentVariablesField());
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return rootPanel;
    }

    public BuildConfigurationTabComponent(){
        comboBox1.addItem("CMake");
        comboBox2.addItem("test_program");
        comboBox3.addItem("test_program");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
