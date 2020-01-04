package execution;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseExecutionControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void execute() {
        System.out.println(PathEnvironmentVariableUtil.findAllExeFilesInPath("afl-cov"));
        System.out.println(PathEnvironmentVariableUtil.findAllExeFilesInPath("gcc"));
        System.out.println(PathEnvironmentVariableUtil.findAllExeFilesInPath("java"));
        System.out.println(PathEnvironmentVariableUtil.getPathDirs("cmake"));
        System.out.println(PathEnvironmentVariableUtil.getPathVariableValue());
        System.out.println(PathEnvironmentVariableUtil.findInPath("gi"));
    }
    @Test
    public void executeGeneralCommandLine(){
        GeneralCommandLine generalCommandLine = new GeneralCommandLine("/usr/bin/afl-cov");
        System.out.println(generalCommandLine.getEnvironment());
        System.out.println(generalCommandLine.getExePath());
        System.out.println(generalCommandLine.getParentEnvironment());
        System.out.println(generalCommandLine.getWorkDirectory());

    }
}

