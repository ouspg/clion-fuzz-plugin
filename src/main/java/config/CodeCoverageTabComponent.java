package config;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
public abstract class CodeCoverageTabComponent<Settings extends FuzzerRunConfiguration> extends FuzzerConfigTabComponent<Settings> {
    private JPanel rootPanel;
    private JCheckBox enableCodeCoverageForCheckBox;
    private JPanel fuzzerSpecificPanel;

    public CodeCoverageTabComponent(){
        createEditor(fuzzerSpecificPanel, rootPanel);
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }


    @Override
    public void resetEditorFrom(@NotNull Settings s) {
        if(s.getOptions().getCodeCoverageEnabled() != null) {
            enableCodeCoverageForCheckBox.setSelected(s.getOptions().getCodeCoverageEnabled());
        }
    }

    @Override
    public void applyEditorTo(@NotNull Settings s) {
        s.getOptions().setCodeCoverageEnabled(enableCodeCoverageForCheckBox.isSelected());
    }

    @Override
    @NotNull
    public JComponent createEditor() {
        return rootPanel;
    }
}
