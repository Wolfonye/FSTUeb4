public class MethodUsageData {


    private int numberOfCalls;
    private long summedRuntime;

    public MethodUsageData(long methodRuntime){
        this.numberOfCalls = 1;
        this.summedRuntime = methodRuntime;
    }

    public void update(long time){
        incrementCalls();
        addToRuntime(time);
    }

    private void incrementCalls(){
        numberOfCalls++;
    }

    private void addToRuntime(long time){
        summedRuntime = summedRuntime + time;
    }

    public int getNumberOfCalls() {
        return numberOfCalls;
    }

    public long getSummedRuntime() {
        return summedRuntime;
    }
}
