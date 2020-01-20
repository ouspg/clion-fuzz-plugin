package config.afl;

import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.components.StoredProperty;
import config.FuzzerRunConfigurationOptions;
import org.jetbrains.annotations.Nullable;

public class AflRunConfigurationOptions extends FuzzerRunConfigurationOptions {
    private final StoredProperty<String> testInputDirectory = string(ProjectUtil.getBaseDir()).provideDelegate(this, "testInputDirectory");
    private final StoredProperty<String> fuzzerOutputDirectory = string(null).provideDelegate(this, "fuzzerOutputDirectory");

    public final void setTestInputDirectory(@Nullable String s) {
        testInputDirectory.setValue(this, s);
    }

    public final @Nullable String getTestInputDirectory() {
        return testInputDirectory.getValue(this);
    }

    public final void setFuzzerOutputDirectory(@Nullable String s) {
        fuzzerOutputDirectory.setValue(this, s);
    }

    public final @Nullable String getFuzzerOutputDirectory() {
        return fuzzerOutputDirectory.getValue(this);
    }
}
