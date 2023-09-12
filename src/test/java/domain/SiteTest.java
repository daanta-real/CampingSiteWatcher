package domain;

import com.daanta.camp.domain.Site;
import com.daanta.camp.domain.SiteNanji;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SiteTest {

    Site makeSiteNanji1() {

        // Prepare values
        String url = "";
        Map<String, String> header = new HashMap<>();
        header.put("aIs", "a");
        header.put("bIs", "b");
        Map<String, String> formBody = new HashMap<>();
        formBody.put("id", "idid");
        formBody.put("pw", "[wpw");
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
        log.debug("nanji: {}", nanji);
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
                .header(Map.of(
                        "header1", "h1",
                        "header2", "h2"
                ))
                .formBody(Map.of(
                        "body1", "b1",
                        "body2", "b2"
                ))
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
