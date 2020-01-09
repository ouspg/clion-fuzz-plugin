package config;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.PathUtil;

import java.nio.file.Path;

public class FuzzerRunConfiguration extends RunConfigurationBase<FuzzerRunConfiguration.FuzzerRunConfigurationOptions>
        implements PersistentStateComponent<FuzzerRunConfiguration.FuzzerRunConfigurationOptions> {
    Project myProject;
    protected FuzzerRunConfiguration(@NotNull Project project, @Nullable ConfigurationFactory factory,
                                     @Nullable String name) {
        super(project, factory, name);
        myProject = project;
    }

    @Override
    public @NotNull SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new FuzzerSettingsEditor();
    }

    @Override
    public @Nullable RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment)
            throws ExecutionException {
        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(myProject);
        //netService.configureConsole(consoleBuilder);
        ConsoleView console = consoleBuilder.getConsole();
        try {
            String projectDir = "/home/dennis/CLionProjects/clion-plugin-test-project";
            //OSProcessHandler chgDir = new OSProcessHandler(PathUtil.cmdToGeneralCommandLine("afl-fuzz -i ./input -o ./output ./cmake-build-afl-debug/test_program"));
            OSProcessHandler processHandler = new OSProcessHandler(PathUtil.createFullPathCommandLine("afl-fuzz -i "+ projectDir + "/input -o "+projectDir+"/output2 "+projectDir+"/cmake-build-afl-debug/test_program", "/home/dennis/CLionProjects/clion-plugin-test-project"));
            GeneralCommandLine cmd = PathUtil.createDefaultTtyCommandLine();
            //OSProcessHandler processHandler1
            //processHandler1.setExePath(PathUtil.getExecuteFileFullPath("afl-fuzz"));
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

                ToolWindow toolWindow = ToolWindowManager.getInstance(myProject)
                        .registerToolWindow("AFL", false, ToolWindowAnchor.BOTTOM, myProject, true);
                Content content = ContentFactory.SERVICE.getInstance().createContent(toolWindowPanel, "", false);
                Disposer.register(content, console);
                toolWindow.getContentManager().addContent(content);
                toolWindow.activate(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        }, myProject.getDisposed());
        return null;
    }
    public static final class FuzzerRunConfigurationOptions extends RunConfigurationOptions {
        public FuzzerRunConfigurationOptions(){
        }
    }
}
