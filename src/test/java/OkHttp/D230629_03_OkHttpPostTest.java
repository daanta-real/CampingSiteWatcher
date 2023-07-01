package OkHttp;

import com.daanta.conf.Camps;
import com.daanta.conf.Props;
import com.daanta.domain.Camp;
import com.daanta.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

// Testing OkHttp Fetch Post

@Slf4j
public class D230629_03_OkHttpPostTest {

    private static Camp nanji;

    public ResponseBody campPost(Camp c) throws Exception {

        nanji = Camps.getCamp("nanji");
        ResponseBody result = null;

        // MODE 1. FORM REQUEST
        try{

            Request.Builder reqBuilder = new Request.Builder();

            // URL & HEADER
            reqBuilder.url(c.getUrl());
            for(Map.Entry<String, String> e: c.getHeader().entrySet()) {
                reqBuilder.header(e.getKey(), e.getValue());
            }

            // BODY
            FormBody.Builder form = new FormBody.Builder();
            for(Map.Entry<String, String> e: c.getBody().entrySet()) {
                form.add(e.getKey(), e.getValue());
            }
            RequestBody reqBody = form.build();
            reqBuilder.post(reqBody);

            Request req = reqBuilder.build();

            // SHOOT!
            Response resp = Utils.okHttp.newCall(req).execute(); // Sync
            if (resp.isSuccessful()) {
                result = resp.body();
            } else {
                throw new Exception();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    @Test
    public void testRun() throws Exception {

        // pref
        String targetDate = Props.getInstance().getProperty("targetDate");
        log.debug("TARGET DATE: {}", targetDate);

        // GET ALL INFO AS RESULT
        String responseStr = campPost(nanji).string();
        Map<String, Object> result = Utils.gson.fromJson(responseStr, Map.class);
        //log.debug("\n\nRESULT:\n\n{}\n", Utils.getPrettyJson(result));

        // EXTRACT TARGET INFO FROM RESULT
        Map<String, Object> allDatesResult = (Map<String, Object>) result.get("resultListTm");
        Map<String, String> targetDateResult = (Map<String, String>) allDatesResult.get(targetDate);
        log.debug("TARGET DATE'S RESULT: {}", Utils.getPrettyJson(targetDateResult));

        double availables = Double.parseDouble(String.valueOf(targetDateResult.get("RESVE_POSBL_CNT")));
        log.debug("AVAILABLES: {}", availables);

        boolean isAvailable = availables > 0;
        log.debug("IS RESERVATION AVAILABLE?: {}", isAvailable);
        log.debug("\n\nIN CONCLUSION, AT {}, {} ARE AVAILABLE, SO RESERVATION IS {}.",
                targetDate, availables, isAvailable ? "OPEN" : "CLOSED");

    }

}
