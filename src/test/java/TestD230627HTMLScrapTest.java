import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

// HTML Scrapping test

@Slf4j
public class TestD230627HTMLScrapTest {


    private static String httpFetch_GET(String targetURL) {

        StringBuilder htmlContent = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            URL url = new URI(targetURL).toURL();
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                htmlContent.append(line);
                htmlContent.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return htmlContent.toString();

    }

//    public static String getPrettyJSON(Object o) {
//        Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
//        return gson.toJson(o);
//    }

    String targetURL = "https://yeyak.seoul.go.kr/web/reservation/selectReservView.do?rsv_svc_id=S230612170547813621&code=T500&dCode=T502&sch_order=1&sch_choose_list=&sch_type=&sch_text=2%EC%9D%B8%EC%9A%A9&sch_recpt_begin_dt=&sch_recpt_end_dt=&sch_use_begin_dt=&sch_use_end_dt=&svc_prior=N&sch_reqst_value=";

    @Test
    public void htmlScrapTest1() {
        log.debug("HTTP REQUEST가 잘 작동하는지 테스트합니다.");
        log.debug("URL: {}", targetURL);
        String result = httpFetch_GET(targetURL);
        log.debug("결과: {}", result);
    }

}
