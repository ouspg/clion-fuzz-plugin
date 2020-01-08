package config;

import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.util.Set;

public class FuzzerConfigurationPanel extends JPanel{
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;

    public FuzzerConfigurationPanel(){

        tabbedPane.setComponentAt(0, new BuildConfigurationTabComponent().getRootPanel());
        //tabbedPane.add("Build", new BuildConfigurationTabComponent().getRootPanel());
       // tabbedPane.add("Code Coverage", new CodeCoverageTabComponent().getRootPanel());
    }
    public JPanel getRootPanel(){
        return rootPanel;
    }

}
