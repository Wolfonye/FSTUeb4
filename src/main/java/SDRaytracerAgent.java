//Philipp Bous - 912590

import java.lang.instrument.Instrumentation;
public class SDRaytracerAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new RaytracerTransformer());
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                Statistics.writeStatisticsToFile();
            }
        });
    }
}
