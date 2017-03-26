package sk.kukla.jurcisin.vincent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by vincent on 3/25/17.
 */
public class Server implements Service, Runnable {

    private int sharedCounter;
    private int maxCounterValue;
    private LinkedBlockingQueue<FutureTask<Integer>> queue;
    private ExecutorService executor;

    public Server(int maxCounterValue) {
        this.sharedCounter = 0;
        this.maxCounterValue = maxCounterValue;
        this.queue = new LinkedBlockingQueue<>();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public Future<Integer> getResult() {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> sharedCounter++);
        queue.add(integerFutureTask);
        return integerFutureTask;
    }

    public void run() {
        while (sharedCounter < maxCounterValue) {
            if (!queue.isEmpty()) {
                executor.execute(queue.poll());
            }
        }
    }

    public int getSharedCounter() {
        return sharedCounter;
    }
}
