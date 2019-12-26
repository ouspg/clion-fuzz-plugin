import com.intellij.ide.plugins.PluginManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ScriptRunner {
    public static void run(String script){
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", script});
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "\n running script : " + script + "\n";
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                line += currentLine + "\n";
            }
            PluginManager.getLogger().error(line);
            process.waitFor();
        } catch (Throwable e) {
            PluginManager.getLogger().error(e);
        }
    }
}
