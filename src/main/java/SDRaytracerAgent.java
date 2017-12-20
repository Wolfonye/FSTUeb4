/**
 * Created by bous on 12.12.17.
 */
import java.lang.instrument.Instrumentation;
public class SDRaytracerAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("hallo");
        inst.addTransformer(new RaytracerTransformer());
    }
}
