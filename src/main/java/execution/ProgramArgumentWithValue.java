package execution;

public class ProgramArgumentWithValue extends ProgramArgument {
    private final String paramValue;
    public ProgramArgumentWithValue(String param, String paramValue){
        super(param);
        this.param = param;
        this.paramValue = paramValue;
    }
    public String getParamValue() {
        return paramValue;
    }
    public String toString(){
        return param + " " + paramValue;
    }
}
