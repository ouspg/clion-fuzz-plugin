package config;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.util.Pair;
import com.intellij.uiDesigner.core.GridConstraints;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class FuzzerConfigTabComponent<T extends FuzzerRunConfiguration> extends SettingsEditorGroup<T> implements ConfigurationTab{
    private JPanel rootPanel;
    private JPanel fuzzerSpecificPanel;

    public void createEditor(JPanel fuzzerSpecificPanel, JPanel rootPanel){
        this.rootPanel = rootPanel;
        this.fuzzerSpecificPanel = fuzzerSpecificPanel;
    }



    @Override
    @NotNull
    public JComponent createEditor() {
        for(Pair<String, SettingsEditor<T>> editor: getEditors()){
            GridConstraints constraints = new GridConstraints();
            constraints.setFill(GridConstraints.FILL_HORIZONTAL);
            fuzzerSpecificPanel.add(editor.second.getComponent(), constraints);
        }
        return rootPanel;
    }
}
