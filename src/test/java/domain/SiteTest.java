package domain;

import com.daanta.camp.domain.Site;
import com.daanta.camp.domain.SiteNanji;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SiteTest {

    @Test
    void siteObjCreationTest() {

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
        Site nanji = SiteNanji.builder()
                .url(url)
                .header(header)
                .formBody(formBody)
                .query(query)
                .build();

        // Verify
        log.debug("\nA SITE INSTANCE:\n{}\n", nanji);

    }

}
