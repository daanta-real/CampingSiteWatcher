package domain;

import com.daanta.camp.domain.Target;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
public class TargetTest {

    @Test
    void targetTest() {

        String url = "";
        Map<String, String> header = new HashMap<>();
        header.put("aIs", "a");
        header.put("bIs", "b");
        Map<String, String> formBody = new HashMap<>();
        formBody.put("id", "");
        formBody.put("pw", "");
        String query = "div";

        Target target = Target.builder()
            .url(url)
            .header(header)
            .formBody(formBody)
            .query(query)
            .build();

        log.debug("{}", target);
        assertNotNull(target);

    }

}
