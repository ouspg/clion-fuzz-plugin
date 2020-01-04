package config;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FuzzerSettingsEditor extends SettingsEditor<FuzzerRunConfiguration> {
    private JPanel rootPanel;
    private LabeledComponent<ComponentWithBrowseButton> testComponent;

    @Override
    protected void resetEditorFrom(@NotNull FuzzerRunConfiguration s) {

    }

    @Override
    protected void applyEditorTo(@NotNull FuzzerRunConfiguration s) throws ConfigurationException {

    }

    @Override
    protected @NotNull JComponent createEditor() {
        rootPanel = new FuzzerConfigurationPanel().getRootPanel();
        return rootPanel;
    }

    private void createUIComponents(){
        testComponent = new LabeledComponent<ComponentWithBrowseButton>();
        testComponent.setComponent(new TextFieldWithBrowseButton());
    }
}
