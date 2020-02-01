package config.afl;

import com.intellij.execution.configurations.RunConfiguration;
import config.FuzzerConfigurationTabComponent;
import config.FuzzerRunConfiguration;

import java.util.Set;

public class AflFuzzerConfigurationTabComponent<Settings extends AflRunConfiguration> extends FuzzerConfigurationTabComponent<Settings> {
    AflFuzzerConfigurationTabComponent(){
        addEditor("AFL Fuzzer Config Editor", new AflFuzzConfigPanel<>());
    }
}
