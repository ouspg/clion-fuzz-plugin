package config.afl;

import config.FuzzerConfigurationTabComponent;
import config.FuzzerRunConfiguration;

import java.util.Set;

public class AflFuzzerConfigurationTabComponent<Settings extends AflRunConfiguration> extends FuzzerConfigurationTabComponent<Settings> {
    AflFuzzerConfigurationTabComponent(){
        addEditor("Adding editor", new AflFuzzConfigPanel<>());
    }
}
