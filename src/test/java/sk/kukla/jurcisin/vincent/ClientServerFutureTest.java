package sk.kukla.jurcisin.vincent;

import org.awaitility.Awaitility;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by vincent on 3/25/17.
 */
public class ClientServerFutureTest {

    @Test
    public void futureTest() throws Exception {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final int counterMax = 100;
        final Server myServer = new Server(counterMax);
        executor.execute(myServer);
        for (int i=0; i<counterMax; i++) {
            executor.submit(new Client(myServer));
        }
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS)
                .until(myServer::getSharedCounter, equalTo(counterMax));
    }
}
