package domain;

import com.daanta.camp.domain.Site;
import com.daanta.camp.domain.SiteNanji;
import com.daanta.camp.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SiteTest {

    Site makeSiteNanji1() {

        // Prepare values
        String url = "";

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("aIs", "a");
        headerMap.put("bIs", "b");
        String header = Utils.keyValMapToString(headerMap);

        Map<String, String> formBodyMap = new HashMap<>();
        formBodyMap.put("id", "idid");
        formBodyMap.put("pw", "[wpw");
        String formBody = Utils.keyValMapToString(formBodyMap);

        String query = "div";

        // Build
        Site site = SiteNanji.builder()
                .url(url)
                .header(header)
                .formBody(formBody)
                .query(query)
                .build();

        log.debug("\nA SITE INSTANCE:\n{}\n", site);
        return site;

    }

    @Test
    void makeSiteNanji1Test() {
        Site siteNanji1 = makeSiteNanji1();
    }

    Site makeSiteNanji2() {
        Site nanji = SiteNanji.builder()
            .url("url")
            .header(null)
            .formBody(null)
            .query(null)
            .build();
        log.debug("\nnanji: {}", nanji);
        return nanji;
    }

    @Test
    void makeSiteNanji2Test() {
        Site nanji = makeSiteNanji2();
    }

    public static Site makeSiteNanji3() {
        Site nanji = SiteNanji.builder()
                .nm("Website name")
                .url("https://www.naver.com/")
                .query("q")
                .header(Utils.keyValMapToString(Map.of(
                        "header1", "h1",
                        "header2", "h2"
                )))
                .formBody(Utils.keyValMapToString(Map.of(
                        "body1", "b1",
                        "body2", "b2"
                )))
                .lastval("recent value")
                .build();
        log.debug("nanji: {}", nanji);
        return nanji;
    }

    @Test
    void makeSiteNanji3Test() {
        Site nanji = makeSiteNanji3();
    }

}
