package settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import settings.views.PluginSettingsWindow;

import javax.swing.*;

public class PluginSettingsConfigurable implements SearchableConfigurable {
    PluginSettingsConfig pluginSettingsConfig;
    PluginSettingsWindow pluginSettingsWindow;
    private final Project projectInstance;

    public PluginSettingsConfigurable(@NotNull Project projectInstance) {
        this.projectInstance = projectInstance;
        pluginSettingsConfig = PluginSettingsConfig.getInstance(projectInstance);
    }

    @Override
    public @NotNull String getId() {
        return "preference.PluginSettingsConfigurable";
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Fuzzer";
    }

    @Override
    public @Nullable JComponent createComponent() {
        pluginSettingsWindow = new PluginSettingsWindow(projectInstance);
        pluginSettingsConfig = PluginSettingsConfig.getInstance(projectInstance);
        return pluginSettingsWindow.getRootPanel();
    }

    @Override
    public boolean isModified() {
        return pluginSettingsWindow.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        pluginSettingsWindow.apply();
    }
}
