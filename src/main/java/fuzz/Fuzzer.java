package fuzz;

import java.util.List;

public interface Fuzzer {
    public void startFuzzing();
    public void ceaseFuzzing();
    public FuzzResults getResults();
    public List<Crash> getCrashes();
}
