package cz.netcoop;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Timer {
    public static void wait(int milliseconds) throws TimeoutException {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new TimeoutException();
        }
    }
}
