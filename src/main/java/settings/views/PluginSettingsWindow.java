package settings.views;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import settings.PluginSettingsConfig;

import javax.swing.*;

public class PluginSettingsWindow {
    private JComponent rootPanel;
    private JCheckBox automaticallyLookForToolsCheckBox;
    private TextFieldWithBrowseButton pathToBinaries;
    private PluginSettingsConfig config;

    public PluginSettingsWindow(Project projectInstance) {
        config = PluginSettingsConfig.getInstance(projectInstance);
        assert config != null;
        pathToBinaries.setText(config.defaultPathToBinaries);
        automaticallyLookForToolsCheckBox.setSelected(config.automaticallyLookForToolBinaries);
        automaticallyLookForToolsCheckBox.addActionListener(actionEvent -> System.out.println("This will print if the checkbox is enabled or not: " + automaticallyLookForToolsCheckBox.isSelected()));
        FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(false, true,
                false, false, false, false);
        pathToBinaries.addBrowseFolderListener(new TextBrowseFolderListener(fileChooserDescriptor));
    }
    public JComponent getRootPanel() {
        return rootPanel;
    }

    public boolean isModified() {
        boolean modified = false;
        modified |= !pathToBinaries.getText().equals(config.getDefaultPathToBinaries());
        modified |= !pathToBinaries.getText().equals(config.getAutomaticallyLookForToolBinaries());
        return modified;
    }

    public void apply() {
        config.setAutomaticallyLookForToolBinaries(automaticallyLookForToolsCheckBox.isSelected());
        config.setDefaultPathToBinaries(pathToBinaries.getText());
    }
}
