package config.afl;

import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import config.BuildConfigurationTabComponent;
import config.CodeCoverageTabComponent;
import config.FuzzerConfigurationTabComponent;
import config.FuzzerRunConfiguration;
import org.jetbrains.annotations.NotNull;

public class AflBuildConfigurationTabComponent<Settings extends AflRunConfiguration>  extends BuildConfigurationTabComponent<Settings> {

}
