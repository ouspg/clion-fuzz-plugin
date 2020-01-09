package config;

import javax.swing.*;

public class BuildConfigurationTabComponent extends JComponent{
    private JPanel rootPanel;
    private JPanel buildSpecificOptionsPanel;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    public BuildConfigurationTabComponent(){
        comboBox1.addItem("CMake");
        comboBox2.addItem("test_program");
        comboBox3.addItem("test_program");
    }

    private void createUIComponents() {

        // TODO: place custom component creation code here
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
