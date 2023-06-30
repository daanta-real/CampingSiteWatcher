package Threads;

import com.daanta.domain.CampManager;
import com.daanta.conf.Props;
import com.daanta.domain.Camp;
import com.daanta.utils.HTTPRequester;
import com.daanta.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
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

    public boolean checkNanji_noLog() throws Exception {

        // pref
        Camp nanji = CampManager.getCamp("nanji");
        String targetDate = Props.getInstance().getProperty("targetDate");

        // GET ALL INFO AS RESULT
        String responseStr = HTTPRequester.post(nanji).string();
        Map<String, Object> result = Utils.gson.fromJson(responseStr, Map.class);

        // EXTRACT TARGET INFO FROM RESULT
        Map<String, Object> allDatesResult = (Map<String, Object>) result.get("resultListTm");
        Map<String, String> targetDateResult = (Map<String, String>) allDatesResult.get(targetDate);

        double availables = Double.parseDouble(String.valueOf(targetDateResult.get("RESVE_POSBL_CNT")));

        return availables > 0;

    }

    @Test
    public void checkReservation_nanji() throws Exception {

        // Set and run the scheduled behavior
        // It will repeat not every 10 secs but after the previous action cycle has been finished
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            boolean result;
            try {
                result = checkNanji_noLog();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            log.debug("확인: {}", result);
        }, 0, 10, TimeUnit.SECONDS);

//        // Turn on as it is for 60 secs, then you can find 6 ~ 7 messages
//        Thread.sleep(60000);
//
//        // After 60 secs Shutdown the service and exit the app
//        service.shutdown();

        // Sleep indefinitely to keep the task running
        Thread.sleep(Long.MAX_VALUE);

    }


}
