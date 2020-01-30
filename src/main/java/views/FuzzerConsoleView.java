package views;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ui.BaseContentCloseListener;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.docking.DockManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.terminal.LocalTerminalDirectRunner;
import org.jetbrains.plugins.terminal.TerminalView;
import org.jetbrains.plugins.terminal.action.RenameTerminalSessionAction;
import windows.FuzzerConsoleToolWindowFactory;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FuzzerConsoleView extends TerminalView implements FocusListener, ProjectComponent {
    private final Project project;
    private ToolWindow fuzzerToolWindow;

    /**
     * Initialisation of service - it's a type of project service
     * @param project Return the console associated with current project
     * @return
     */
    public static FuzzerConsoleView getInstance(Project project) {
        return project.getComponent(FuzzerConsoleView.class);
    }

    /**
     * Only one console per project -- Even if another library extends current project
     * @param project Initialise the console for current project
     */
    public FuzzerConsoleView(Project project) {
        super(project);
        this.project = project;
    }



    public void init(ToolWindow toolWindow) {
        System.out.println("New tool window opened!!! just clicked it babe!!!");
        if (toolWindow == null){
            return;
        }
        fuzzerToolWindow = toolWindow;
        ((ToolWindowImpl)fuzzerToolWindow).setTabActions(new DumbAwareAction("New Session", "Create new session", AllIcons.General.Add) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                System.out.println("New tabopen clicked");
            }
        });
        ((ToolWindowImpl)fuzzerToolWindow).setTabDoubleClickActions(new RenameTerminalSessionAction());
        toolWindow.setToHideOnEmptyContent(true);
        fuzzerToolWindow.setToHideOnEmptyContent(true);
    }

    private void createConsoleWindow() {
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }

    @Override
    public @NotNull String getComponentName() {
        return "Fuzzer";
    }

    public void showExecutionResult(DefaultExecutionResult executionResult) {
//        if (fuzzerToolWindow == null){
//            FuzzerConsoleView.getInstance(project).init(fuzzerToolWindow);
//        }
        ContentManager contentManager = fuzzerToolWindow.getContentManager();
        SimpleToolWindowPanel panel = new SimpleToolWindowPanel(false, true);
        panel.setContent(executionResult.getExecutionConsole().getComponent());
        //final Content content = ContentFactory.SERVICE.getInstance().createContent(panel, "Tab 1", false);
        //content.setShouldDisposeContent(true);
        //content.setDisposer(executionResult.getExecutionConsole());
        //content.setCloseable(true);
        //contentManager.addContent(content);
    }
}

