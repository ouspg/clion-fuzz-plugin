package utils;


import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.PathEnvironmentVariableUtil;
import com.intellij.execution.configurations.PtyCommandLine;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.EnvironmentUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.execution.ParametersListUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.OSUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utils for find some dirs.
 * Created by beansoft on 2017/3/14.
 */
public class PathUtil {
    public static final String RN_CONSOLE = ".rnconsole";
    public static final String PACKAGE_JSON = "package.json";
    public static final String KEY_METRO_PORT = "metroPort";
    public static String GRADLE_FILE = "build.gradle";
    public static String _IDEA_DIR = ".idea" + File.separator;
    public static String RN_CONSOLE_FILE = _IDEA_DIR + RN_CONSOLE;
    // add rnconsole config file to .idea project @since 1.0.8


    /**
     * Ensure get the config file created.
     * @param project
     * @return config file
     */
    private static File initConfigFileDir(Project project) {
        String path = project.getBasePath();
        File ideaFolder = new File(path, _IDEA_DIR);
        if(!ideaFolder.exists()) {
            ideaFolder.mkdirs();
        }
        return new File(path, RN_CONSOLE_FILE);
    }



    /**
     * Get the real android project root path, which contains build.gradle file.
     *
     * @param inputDir root search dir
     * @return
     */
    public static String getAndroidProjectPath(String inputDir) {
        File file = new File(inputDir, GRADLE_FILE);
        // Search root
        if (file.exists()) {
            return inputDir;
        } else {
            // search sub folders which might contains build.gradle file
            File[] subfolders = new File(inputDir).listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory();
                }
            });

            if (subfolders != null) {
                for (File dir : subfolders) {
                    file = new File(dir.getAbsolutePath() + File.separatorChar + GRADLE_FILE);
                    if (file.exists()) {
                        return dir.getAbsolutePath();
                    }
                }
            }
        }

//        Messages.showWarningDialog(project, "找不到有效的React Native目录, 命令将停止执行.\n目录" +
//                inputDir + "及其上级目录中找不到有效的package.json文件.", "警告");

        return null;
    }



    /**
     * Get the full path of an exe file by the IDEA platform.
     * On Mac sys, PATH might will not be directly access by the IDE code,
     * eg: Android Studio Runtime.exec('adb') will throw a no file or directory exception.
     *
     * @param exeName
     * @return
     */
    public static String getExecuteFileFullPath(String exeName) {
        String fullPath = exeName;
        if (OSUtils.isWindows()) {
            if (!exeName.endsWith(".exe")) {
                // first try exe
                fullPath = getExecuteFullPathSingle(exeName + ".exe");
                if (fullPath != null) {
                    return fullPath;
                }

                if (!exeName.endsWith(".cmd")) {
                    // Fix bug: npm, react-native can't be run Windows: https://github.com/beansoftapp/react-native-console/issues/6
                /*
                Unable to run the commandline:Cannot run program "D:\nodejs\npm" (in directory "D:\Project\wxsh"):
                CreateProcess error=193, %1 不是有效的 Win32 应用程序
                 */
                    fullPath = getExecuteFullPathSingle(exeName + ".cmd");
                    if (fullPath != null) {
                        return fullPath;
                    }
                }

                if (!exeName.endsWith(".bat")) {
                    // Fix bug: gradlew can't be run Windows:
                /*
                Unable to run the commandline:Cannot run program ".\gradlew.cmd"
                 (in directory "D:\Project\wxsh\android"): CreateProcess error=2, 系统找不到指定的文件。
                 there is only a gradlew.bat file, no gradlew.cmd file
                 */
                    fullPath = getExecuteFullPathSingle(exeName + ".bat");
                    if (fullPath != null) {
                        return fullPath;
                    }
                }
            }
        }
        fullPath = getExecuteFullPathSingle(exeName);

        return fullPath;
    }


    public static String getExecuteFullPathSingle(String exeName) {
        List<File> fromPath = PathEnvironmentVariableUtil.findAllExeFilesInPath(exeName);
        if (fromPath != null && fromPath.size() > 0) {
            return fromPath.get(0).toString();
        }
        return null;
    }


    public static GeneralCommandLine cmdToGeneralCommandLine(String cmd) {
        GeneralCommandLine commandLine = new GeneralCommandLine(cmd.split(" "));
        commandLine.setCharset(Charset.forName("UTF-8"));
        return commandLine;
    }

    public static GeneralCommandLine createDefaultTtyCommandLine() {
        // here just run one command: python freeline.py
        PtyCommandLine commandLine = new PtyCommandLine();
        if (!SystemInfo.isWindows) {
            commandLine.getEnvironment().put("TERM", "xterm-256color");
        }
//        commandLine.withConsoleMode(false);
//        commandLine.withInitialColumns(120);
//        ExecutionEnvironment environment = getEnvironment();
//        commandLine.setWorkDirectory(environment.getProject().getBasePath());
        String defaultShell = ObjectUtils.notNull(EnvironmentUtil.getValue("BASH"), "/bin/bash");
        commandLine.setExePath(defaultShell);
//            commandLine.setExePath("npm");
//            commandLine.addParameters("run-script");
//            commandLine.addParameters("start");
        return commandLine;
    }

    /**
     * Create full path command line.
     * @param shell
     * @param workDirectory - only used on windows for gradlew.bat
     * @return GeneralCommandLine
     */
    @NotNull
    public static GeneralCommandLine createFullPathCommandLine(String shell,
                                                               @Nullable String workDirectory) {
        String[] cmds = shell.split(" ");
        String exeFullPath;
        GeneralCommandLine commandLine = null;
        if (cmds.length > 1) {
            String exePath = cmds[0];

            List<String> cmdList = new ArrayList<>();
            cmdList.addAll(Arrays.asList(cmds));
            exeFullPath = getExecuteFileFullPath(exePath);
            if (exeFullPath == null) {
                exeFullPath = exePath;
            }

            // https://github.com/beansoftapp/react-native-console/issues/8
            if (OSUtils.isWindows()) {
                if(exeFullPath.equals("gradlew.bat")) {
                    exeFullPath = workDirectory + File.separator + exeFullPath;
                }
            }

            cmdList.remove(0);
            cmdList.add(0, exeFullPath);

            commandLine = new GeneralCommandLine(exeFullPath);

            // Fix param with quotes issue, see com.intellij.diff.tools.external.ExternalDiffToolUtil, https://github.com/beansoftapp/react-native-console/issues/31
            List<String> parameters = ParametersListUtil.parse(shell.substring(exePath.length()), false);// keepQuotes: false

            commandLine.addParameters(parameters);

        } else {
            exeFullPath = getExecuteFileFullPath(shell);
            if (exeFullPath == null) {
                exeFullPath = shell;
            }

            commandLine = new GeneralCommandLine(exeFullPath);
        }

        commandLine.setCharset(Charset.forName("UTF-8"));// fix adb output on windows can't be read as UTF-8 causes Chinese not displayed

        return commandLine;
    }
}
