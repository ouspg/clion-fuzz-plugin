package windows;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import settings.CMakeConfigurationSetup;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import utils.PathUtil;

public class FuzzerConsoleToolWindowFactory implements ToolWindowFactory {
    public String TOOL_WINDOW_ID = "Fuzzer Console";
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        FuzzerToolWindow fuzzerToolWindow = new FuzzerToolWindow("AFL");
        createConsole(project);
    }

    private void createConsole(Project project) {
        new CMakeConfigurationSetup(project);
        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
        //netService.configureConsole(consoleBuilder);
        ConsoleView console = consoleBuilder.getConsole();
        try {
            OSProcessHandler processHandler = new OSProcessHandler(PathUtil.createFullPathCommandLine("echo \"\"", "/home/dennis"));
            console.attachToProcess(processHandler);
            processHandler.startNotify();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                ActionGroup actionGroup = new DefaultActionGroup();
                ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, actionGroup, false);

                SimpleToolWindowPanel toolWindowPanel = new SimpleToolWindowPanel(false, true);
                toolWindowPanel.setContent(console.getComponent());
                toolWindowPanel.setToolbar(toolbar.getComponent());

                ToolWindow toolWindow = ToolWindowManager.getInstance(project)
                        .registerToolWindow("New Window", false, ToolWindowAnchor.BOTTOM, project, true);
                //toolWindow.setIcon(netService.getConsoleToolWindowIcon());

                Content content = ContentFactory.SERVICE.getInstance().createContent(toolWindowPanel, "", false);
                Disposer.register(content, console);

                toolWindow.getContentManager().addContent(content);
            }
        }, project.getDisposed());
    }
}
