import libraries.HTMLFetch;
import libraries.Props;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

@Slf4j
public final class D230627_02_NanjiTest {

    private static Properties p;
    @BeforeAll
    public static void loadProp() {
        p = Props.getInstance();
    }

    @Test
    public void checkNanji() {

        // check props
        String url = p.getProperty("nanjiUrl");
        String option = p.getProperty("nanjPOption");
        log.debug("URL: {}", url);
        log.debug("option: {}", option);

        // fetch
        String html = HTMLFetch.httpFetch_GET(url);
        log.debug("FETCHED HTML:\n{}", html);

//        Document doc = Jsoup.parse(html);
//        Element spanEl = doc.selectFirst("div#div_cal_20230701 > span.num");
//        assert spanEl != null;
//        String text = String.valueOf(Optional.of(spanEl.text()));
//        log.debug("결과: {}", text);

    }

}
