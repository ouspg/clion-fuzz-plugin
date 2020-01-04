package settings;

import com.intellij.openapi.components.PersistentStateComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverageSettingsConfig implements PersistentStateComponent<CoverageSettingsConfig> {
    @Nullable
    @Override
    public CoverageSettingsConfig getState() {
        return null;
    }

    @Override
    public void loadState(@NotNull CoverageSettingsConfig state) {

    }
}
