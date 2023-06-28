package libraries;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Props {

    private static Properties instance;
    private Props() {
    }

    public static Properties getInstance() {
        if(instance != null) return instance;
        instance = new Properties();
        try (InputStream is = Props.class.getClassLoader().getResourceAsStream("config.properties")) {
            log.debug("\n\n<<< LOADING ALL PROPS START >>>");
            instance.load(is);
            log.debug("PROPERTIES LIST: {}", instance.stringPropertyNames());
            for (String key : instance.stringPropertyNames()) {
                String value = instance.getProperty(key);
                log.debug(" - PROPERTY [{}] = [{}]", key, value);
            }
            log.debug("\n<<< LOADING ALL PROPS FINISHED >>>\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

}
