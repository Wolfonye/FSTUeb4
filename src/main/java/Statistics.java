//Philipp Bous - 912590

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public final class Statistics {
    private static HashMap<String, MethodUsageData> methods;

    private Statistics(){
    }

    public static void writeStatisticsToFile(){
        String methodName;
        MethodUsageData data;
        int totalCalls = 0;
        long totalRuntime = 0;

        try(FileWriter fileWriter = new FileWriter(new File("statistics.txt")); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            bufferedWriter.write("---------------------------------------------SDRaytracer - Basic Statistics-------------------------------------------------------");
            bufferedWriter.newLine();
            for (Iterator iterator = methods.entrySet().iterator(); iterator.hasNext(); ) {
                HashMap.Entry<String, MethodUsageData> entry = (HashMap.Entry<String, MethodUsageData>) iterator.next();
                methodName = entry.getKey();
                data = entry.getValue();
                totalCalls = totalCalls + data.getNumberOfCalls();
                totalRuntime = totalRuntime + data.getSummedRuntime();
                bufferedWriter.write("Methodname: '" + methodName
                                    + "'; Number of Calls: " + data.getNumberOfCalls()
                                    + "; Average Runtime of This Method (Seconds): " + nanoSecondsToSeconds(data.getSummedRuntime())/(double)data.getNumberOfCalls());
                bufferedWriter.newLine();
                /*System.out.println("Method: " + entry.getKey() + "; number of calls: " + entry.getValue().getNumberOfCalls()
                        + "; summed runtime: " + entry.getValue().getSummedRuntime()
                        + "; average runtime: " + (nanoSecondsToSeconds(entry.getValue().getSummedRuntime()) / entry.getValue().getNumberOfCalls()));*/
                iterator.remove();
            }
            bufferedWriter.write("----------------------------------------------------------------------------------------------------------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write("Average Runtime (ALL Methods): " + nanoSecondsToSeconds(totalRuntime)/(double)totalCalls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void visitedMethod(String nameOfMethod, long methodRuntime){
        if (methods == null){
            methods = new HashMap<String, MethodUsageData>();
        }
        if(methods.containsKey(nameOfMethod)) {
            MethodUsageData data = methods.get(nameOfMethod);
            data.update(methodRuntime);
        }else{
            MethodUsageData data = new MethodUsageData(methodRuntime);
            methods.put(nameOfMethod,data);
        }
    }

    private static double nanoSecondsToSeconds(long nanoseconds){
        return ((double) nanoseconds) / 1000000000.0;
    }
}
