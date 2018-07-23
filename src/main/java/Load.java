
import java.lang.instrument.Instrumentation;

public class Load {
   
    public static void agentmain(String args, Instrumentation inst) {
        inst.addTransformer(new ClassTransformer());
    }
}
