package sk.kukla.jurcisin.vincent;

import java.util.concurrent.Future;

/**
 * Created by vincent on 3/25/17.
 */
public interface Service {

    public Future<Integer> getResult();
}
