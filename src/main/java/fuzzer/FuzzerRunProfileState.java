package fuzzer;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.PtyCommandLine;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.sh.run.ShTerminalRunner;
import com.intellij.terminal.ProcessHandlerTtyConnector;
import com.intellij.terminal.TerminalExecutionConsole;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.terminal.ShellTerminalWidget;
import views.FuzzerConsoleView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This is only for feature testing do not mess with anything else
 */
public class FuzzerRunProfileState implements RunProfileState {
    ExecutionEnvironment executionEnvironment;
    public FuzzerRunProfileState(ExecutionEnvironment executionEnvironment){
        this.executionEnvironment = executionEnvironment;
    }

    @Override
    public @Nullable ExecutionResult execute(Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
        System.out.println("Executor called");
        PtyCommandLine generalCommandLine = new PtyCommandLine();
//        GeneralCommandLine generalCommandLine = new GeneralCommandLine();
        if (!SystemInfo.isWindows) {
            generalCommandLine.getEnvironment().put("TERM", "xterm-256color");
        }
        //generalCommandLine.withConsoleMode(false);
        //generalCommandLine.withInitialColumns(120);
        generalCommandLine.setExePath("/usr/bin/afl-fuzz");
        Map<String, String> map = new HashMap<>();
        map.put("AFL_SKIP_CPUFREQ", "1");
        generalCommandLine.withEnvironment(map);
//        ArrayList<String> params = new ArrayList<String>(Arrays.asList("-fprofile-arcs", "-ftest-coverage", "-o", "program_under", "main.cpp", "main.h", "functions.h", "hello.cpp", "factorial.cpp"));
        ArrayList<String> params = new ArrayList<String>(Arrays.asList("-i", "input", "-o", "output", "./program_under"));
        generalCommandLine.addParameters(params);
        generalCommandLine.setWorkDirectory("/home/dennis/CLionProjects/clion-plugin-test-project/");
//        ConsoleView executionConsole = TextConsoleBuilderFactory.getInstance().createBuilder(executionEnvironment.getProject()).getConsole();
        KillableColoredProcessHandler processHandler = new KillableColoredProcessHandler(generalCommandLine);
        ConsoleView executionConsole = new TerminalExecutionConsole(executionEnvironment.getProject(), null);
        processHandler.startNotify();
        executionConsole.attachToProcess(processHandler);
        FuzzerConsoleView.getInstance(executionEnvironment.getProject()).showExecutionResult(new DefaultExecutionResult(executionConsole, processHandler));
        return null;
    }
}
