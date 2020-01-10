package config;

import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.fields.ExpandableTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EnvironmentSettingsUIComponent extends JComponent{
    private JPanel rootPanel;
    private ExpandableTextField programArgumentsField;
    private TextFieldWithBrowseButton workingDirectoryField;
    private EnvironmentVariablesTextFieldWithBrowseButton environmentVariablesField;

    public String getProgramArgumentsField(){
        return programArgumentsField.getText();
    }
    public String getWorkingDirectoryField(){
        return workingDirectoryField.getText();
    }
    public String getEnvironmentVariablesField(){
        return environmentVariablesField.getText();
    }

    public void setProgramArgumentsField(String s){
        programArgumentsField.setText(s);
    }
    public void setWorkingDirectoryField(String s){
        workingDirectoryField.setText(s);
    }
    public void setEnvironmentVariablesField(String s){
        environmentVariablesField.setText(s);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
