package fuzz;

import java.util.List;

public class BaseFuzzer implements Fuzzer{
    String pathToFuzzProgram;
    public BaseFuzzer(String pathToFuzzProgram){
        this.pathToFuzzProgram = pathToFuzzProgram;
    }
    @Override
    public void startFuzzing() {
    }

    @Override
    public void ceaseFuzzing() {

    }

    @Override
    public FuzzResults getResults() {
        return null;
    }

    @Override
    public List<Crash> getCrashes() {
        return null;
    }
}
