package windows;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

public class TasksWindow{
    private JButton executeTaskWindowButton;
    private JPanel myToolWindowContent;

    public TasksWindow(ToolWindow toolWindow) {
        executeTaskWindowButton.addActionListener(e -> taskExecuted());
    }

    private void taskExecuted() {

    }

    public JComponent getContent() {
        return myToolWindowContent;
    }
}
