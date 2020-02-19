package windows;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import coverage.Coverage;
import coverage.LcovCoverageGenerator;
import net.zero9178.cov.editor.CoverageHighlighter;
import net.zero9178.cov.data.CoverageData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CrashesToolWindow {
    private JList<String> list1;
    private JPanel rootPanel;
    private JCheckBox highlightCoverageCheckBox;
    private RefreshCoverageTask refreshCoverageTask;
    Timer t = new Timer();
    public CrashesToolWindow(ToolWindow toolWindow, Project project) {
        list1.setListData(new String[]{"KKKKKKKKKKKKKKKKK", "          "});
        highlightCoverageCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (highlightCoverageCheckBox.isSelected()) {
                    refreshCoverageTask = new RefreshCoverageTask(project);
                    t.schedule(refreshCoverageTask, 1000, 2000);
                }
                else if (!highlightCoverageCheckBox.isSelected()){
                    t.cancel();
                    t.purge();
                }
            }
        });
    }
    public JComponent getContent(){
        return rootPanel;
    }
}

class RefreshCoverageTask extends TimerTask {
    private Project project;
    public RefreshCoverageTask(Project project) {
        this.project = project;
    }
    public void run() {
        ArrayList<Coverage> coverage = LcovCoverageGenerator.parseLcovInfo("/home/dennis/CLionProjects/clion-plugin-test-project/output/cov/lcov/trace.lcov_info_final");
        CoverageData coverageData = LcovCoverageGenerator.getCoverageDataFromCoverage(coverage);
        System.out.println("Its working fine till here!!!");
        CoverageHighlighter.Companion.getInstance(project).setCoverageData(coverageData);
    }
}
