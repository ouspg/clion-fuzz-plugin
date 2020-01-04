package settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(
        name="PluginSettingsConfig",
        storages = {
                @Storage("PluginSettingsConfig.xml")}
)
public class PluginSettingsConfig implements PersistentStateComponent<PluginSettingsConfig> {

    public String getDefaultPathToBinaries() {
        return defaultPathToBinaries;
    }

    public void setDefaultPathToBinaries(String defaultPathToBinaries) {
        this.defaultPathToBinaries = defaultPathToBinaries;
    }

    public String defaultPathToBinaries = "";

    public Boolean getAutomaticallyLookForToolBinaries() {
        return automaticallyLookForToolBinaries;
    }

    public void setAutomaticallyLookForToolBinaries(Boolean automaticallyLookForToolBinaries) {
        this.automaticallyLookForToolBinaries = automaticallyLookForToolBinaries;
    }

    public Boolean automaticallyLookForToolBinaries = false;

    private BuildSettingsConfig buildSettingsConfig;
    private AflSettingsConfig aflSettingsConfig;
    private CoverageSettingsConfig coverageSettingsConfig;

    //PluginSettingsConfig() { }

    @Nullable
    @Override
    public PluginSettingsConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PluginSettingsConfig pluginSettingsConfig) {
        XmlSerializerUtil.copyBean(pluginSettingsConfig, this);
    }
    @Nullable
    public static PluginSettingsConfig getInstance(Project project) {
        return ServiceManager.getService(project, PluginSettingsConfig.class);
    }

}
