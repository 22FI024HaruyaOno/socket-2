import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskObject implements Serializable,ITask{
    int inputNum;
    boolean serverClose;
    List<Integer> primes = new ArrayList<>();

    public int getInputNumber() {
        return inputNum;
    }

    public void setIsClose(boolean close) {
        this.serverClose = close;
    }

    public boolean getIsClose() {
        return this.serverClose;
    }

    @Override
    public void setExecNumber(int x) {
        this.inputNum = x;
    }

    @Override
    public void exec() {
        int n = getInputNumber();
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        
        for (int p = 2; p*p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p*p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        
        for (int p = 2; p <= n; p++) {
            if (isPrime[p]) {
                primes.add(p);
            }
        }
    }

    @Override
    public int getResult() {
        return primes.getLast();
    }
}
