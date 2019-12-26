package execution;

import java.util.List;

public class ProgramArguments {
    List<ProgramArgument> arguments;
    protected void addArgument(ProgramArgument argument){
        arguments.add(argument);
    }
    public String getArgumentsInAString(){
        String str = "";
        for (ProgramArgument argument : arguments){
            str = argument.toString() + " ";
        }
        return str;
    }
}
