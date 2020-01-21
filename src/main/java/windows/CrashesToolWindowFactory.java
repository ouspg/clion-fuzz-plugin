package windows;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import org.jetbrains.annotations.NotNull;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

public class CrashesToolWindowFactory implements ToolWindowFactory {
    public String TOOL_WINDOW_ID = "Fuzzer Crashes";
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        CrashesToolWindow myToolWindow = new CrashesToolWindow(toolWindow, project);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindow.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

}
