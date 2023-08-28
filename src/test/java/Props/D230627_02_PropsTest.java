package Props;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class D230627_02_PropsTest {

    private static Map<String, Object> instance;
    private D230627_02_PropsTest() {
    }

    @Test
    private static void init() {
        instance = new HashMap<>();
        Properties temp = new Properties();
        try (InputStream is = D230627_02_PropsTest.class.getClassLoader().getResourceAsStream("application.properties")) {

            // Load all
            log.debug("\n\n<<< LOADING ALL PROPS START >>>");
            temp.load(is);
            log.debug("PROPERTIES LIST (ALL): {}", temp.stringPropertyNames());

            log.debug("CAMP LIST: {}");

            log.debug("　┌──────────────────────────────────────────────────────────────────────────────────────────");
            for (String key: temp.stringPropertyNames()) {
                String value = temp.getProperty(key);
                log.debug("　│  {} = '{}'", key, value);
                instance.put(key, value);
            }
            log.debug("　└──────────────────────────────────────────────────────────────────────────────────────────");
            log.debug("\n<<< LOADING ALL PROPS FINISHED >>>\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getInstance() {
        if(instance == null) init();
        return instance;
    }

}
