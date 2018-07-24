
import com.martin.record.ClassTransformer;

import java.lang.instrument.Instrumentation;

/**
 * java-agent 代理入口
 */
public class Load {
   
    public static void premain(String args, Instrumentation inst) {
        inst.addTransformer(new ClassTransformer());
    }
}
