package sk.kukla.jurcisin.vincent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by vincent on 3/25/17.
 */
public class Client implements Runnable {

    private Logger LOG = LoggerFactory.getLogger(Client.class);

    private Service service;

    public Client(Service service) {
        this.service = service;
    }

    public void run() {
        Future<Integer> result = service.getResult();
        try {
            LOG.info("returned num: {}", result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
