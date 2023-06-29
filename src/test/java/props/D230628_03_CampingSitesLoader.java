package props;

import libraries.D230629_01_Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
public class D230628_03_CampingSitesLoader {

    @Test
    public void test() {

        // Nanji island
        Map<String, Object> m = D230629_02_PropsNanji.loadCampingSiteProps("nanji");
        log.debug("\n\nCOMPLETE REQUEST:\n\n{}\n", D230629_01_Utils.getPrettyJson(m));



    }
}
