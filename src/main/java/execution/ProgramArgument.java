package execution;

public class ProgramArgument {
    protected String param;
    public ProgramArgument(String param){
        this.param = param;
    }
    public String getParam() {
        return param;
    }
    public String toString(){
        return param;
    }
}
