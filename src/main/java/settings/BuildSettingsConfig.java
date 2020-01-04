package settings;

import com.intellij.openapi.components.PersistentStateComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuildSettingsConfig implements PersistentStateComponent<BuildSettingsConfig> {
    @Nullable
    @Override
    public BuildSettingsConfig getState() {
        return null;
    }

    @Override
    public void loadState(@NotNull BuildSettingsConfig state) {

    }
}
