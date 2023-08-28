package old.Archived;

import old.Props.D230627_02_PropsTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static old.Archived.D230627_02_HTMLFetch.httpFetch_GET;

@Slf4j
public class D230627_01_FetchGETTest {

    private static Map<String, Object> pr;
    @BeforeAll
    public static void loadProp() {
        pr = D230627_02_PropsTest.getInstance();
    }

    @Test
    public void htmlScrapTest1() {
        String targetURL = String.valueOf(pr.get("nanjiUrl"));
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
