package Threads;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class D230629_04_ThreadControl {

    @Test
    public void threadTest1() throws InterruptedException {

        // Set and run the scheduled behavior
        // It will repeat not every 10 secs but after the previous action cycle has been finished
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> log.debug("확인: {}", LocalDate.now()), 0, 10, TimeUnit.SECONDS);

        // Turn on as it is for 60 secs, then you can find 5 ~ 6 messages
        Thread.sleep(60000);

        // After 60 secs Shutdown the service and exit the app
        service.shutdown();

    }


}
