package config.afl;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import config.FuzzerRunConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AflFuzzConfigPanel<Settings extends AflRunConfiguration> extends SettingsEditor<Settings>{
    private JPanel rootPanel;
    private TextFieldWithBrowseButton inputFolder;
    private TextFieldWithBrowseButton outputFolder;

    @Override
    protected void resetEditorFrom(@NotNull Settings s) {
        inputFolder.setText(s.getOptions().getTestInputDirectory());
        outputFolder.setText(s.getOptions().getFuzzerOutputDirectory());
    }

    @Override
    protected void applyEditorTo(@NotNull Settings s) {
        s.getOptions().setTestInputDirectory(inputFolder.getText());
        s.getOptions().setFuzzerOutputDirectory(outputFolder.getText());
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return rootPanel;
    }
}
