package windows;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

public class CrashesToolWindow {
    private JList<String> list1;
    private JPanel rootPanel;

    public CrashesToolWindow(ToolWindow toolWindow) {
        list1.setListData(new String[]{"KKKKKKKKKKKKKKKKK", "          "});
    }
    public JComponent getContent(){
        return rootPanel;
    }
}
