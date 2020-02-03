package windows;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import org.jetbrains.annotations.NotNull;
import views.FuzzerConsoleView;

public class FuzzerConsoleToolWindowFactory implements ToolWindowFactory {
    public String TOOL_WINDOW_ID = "Fuzzer Console";

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        FuzzerConsoleView.getInstance(project).init(toolWindow);
    }
}
