package config;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.components.StoredProperty;
import org.bouncycastle.util.Store;
import org.jetbrains.annotations.Nullable;



//if you want to add more parameters inherit this class and create your own fuzzer!!!
public abstract class FuzzerRunConfigurationOptions extends RunConfigurationOptions {
    //Fuzzer Environment properties
    private final StoredProperty<String> fuzzerProgramArguments = string(null).provideDelegate(this, "fuzzerProgramArguments");
    private final StoredProperty<String> fuzzerWorkingDirectory = string(ProjectUtil.getBaseDir()).provideDelegate(this, "fuzzerWorkingDirectory");
    private final StoredProperty<String> fuzzerEnvironmentVariables = string(null).provideDelegate(this, "fuzzerEnvironmentVariables");
    //Build Environment properties
    private final StoredProperty<String> buildProgramArguments = string(null).provideDelegate(this, "buildProgramArguments");
    private final StoredProperty<String> buildWorkingDirectory = string(ProjectUtil.getBaseDir()).provideDelegate(this, "buildWorkingDirectory");
    private final StoredProperty<String> buildEnvironmentVariables = string(null).provideDelegate(this, "buildEnvironmentVariables");
    //TODO fill it up later after populating the fields
    //Build Selected Build Configurations for e.g. build type and make file should be here!!!

    //Code coverage properties
    //This property could be deleted to have a more user intuitive UI
    private final StoredProperty<Boolean> codeCoverageEnabled = property(false).provideDelegate(this, "codeCoverageEnabled");

    //Getters and Setters for all the properties above
    //Should carefully correlate to the specific fields
    public final @Nullable String getFuzzerProgramArguments() {
        return fuzzerProgramArguments.getValue(this);
    }

    public final void setFuzzerProgramArguments(@Nullable String s) {
        fuzzerProgramArguments.setValue(this, s);
    }

    public final @Nullable String getFuzzerWorkingDirectory() {
        return fuzzerWorkingDirectory.getValue(this);
    }

    public final void setFuzzerWorkingDirectory(@Nullable String s) {
        fuzzerWorkingDirectory.setValue(this, s);
    }

    public final @Nullable String getFuzzerEnvironmentVariables() {
        return fuzzerEnvironmentVariables.getValue(this);
    }

    public final void setFuzzerEnvironmentVariables(@Nullable String s) {
        fuzzerEnvironmentVariables.setValue(this, s);
    }

    public final @Nullable String getBuildProgramArguments() {
        return buildProgramArguments.getValue(this);
    }

    public final void setBuildProgramArguments(@Nullable String s) {
        buildProgramArguments.setValue(this, s);
    }

    public final @Nullable String getBuildWorkingDirectory() {
        return buildWorkingDirectory.getValue(this);
    }

    public final void setBuildWorkingDirectory(@Nullable String s) {
        buildWorkingDirectory.setValue(this, s);
    }

    public final @Nullable String getBuildEnvironmentVariables() {
        return buildEnvironmentVariables.getValue(this);
    }

    public final void setBuildEnvironmentVariables(@Nullable String s) {
        buildEnvironmentVariables.setValue(this, s);
    }

    public final @Nullable Boolean getCodeCoverageEnabled() {
        return codeCoverageEnabled.getValue(this);
    }

    public final void setCodeCoverageEnabled(@Nullable Boolean s) {
        codeCoverageEnabled.setValue(this, s);
    }
}
