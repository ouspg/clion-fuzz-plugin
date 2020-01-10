package config;

import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
public class CodeCoverageTabComponent extends SettingsEditor<FuzzerRunConfiguration> {
    private JPanel rootPanel;
    private JCheckBox enableCodeCoverageForCheckBox;

    public JPanel getRootPanel() {
        return rootPanel;
    }


    @Override
    protected void resetEditorFrom(@NotNull FuzzerRunConfiguration s) {
        if(s.getOptions().getCodeCoverageEnabled() != null) {
            enableCodeCoverageForCheckBox.setSelected(s.getOptions().getCodeCoverageEnabled());
        }
    }

    @Override
    protected void applyEditorTo(@NotNull FuzzerRunConfiguration s) {
        s.getOptions().setCodeCoverageEnabled(enableCodeCoverageForCheckBox.isSelected());
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return rootPanel;
    }
}
