package archived;

import props.D230627_02_Props;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static archived.HTMLFetch.httpFetch_GET;

@Slf4j
public class D230627_01_FetchGETTest {

    private static Properties p;
    @BeforeAll
    public static void loadProp() {
        p = D230627_02_Props.getInstance();
    }

    @Test
    public void htmlScrapTest1() {
        String targetURL = p.getProperty("nanjiUrl");
        log.debug("HTTP REQUEST가 잘 작동하는지 테스트합니다.");
        log.debug("URL: {}", targetURL);
        String result = httpFetch_GET(targetURL);
        log.debug("결과 길이: {}", result.length());
    }

//    public static String getPrettyJSON(Object o) {
//        Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
//        return gson.toJson(o);
//    }

}
