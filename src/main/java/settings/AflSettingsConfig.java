package settings;

import com.intellij.openapi.components.PersistentStateComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AflSettingsConfig implements PersistentStateComponent<AflSettingsConfig> {
    @Nullable
    @Override
    public AflSettingsConfig getState() {
        return null;
    }

    @Override
    public void loadState(@NotNull AflSettingsConfig state) {

    }
}
