package config;

import com.intellij.execution.ui.MacroComboBoxWithBrowseButton;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.module.impl.ModuleImpl;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.structuralsearch.plugin.ui.DirectoryComboBoxWithButtons;
import com.intellij.ui.ComboboxEditorTextField;
import com.intellij.ui.components.fields.ExtendableTextComponent;
import com.intellij.ui.components.fields.ExtendableTextField;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class BuildConfigurationTabComponent extends JComponent{
    private JPanel rootPanel;
    private JPanel buildSpecificOptionsPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    public BuildConfigurationTabComponent(){

    }

    private void createUIComponents() {

        // TODO: place custom component creation code here
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
