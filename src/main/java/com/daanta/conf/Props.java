package com.daanta.conf;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Props {

    private static Properties instance;
    private Props() {
    }

    private static void init() {
        instance = new Properties();
        try (InputStream is = Props.class.getClassLoader().getResourceAsStream("config.properties")) {
            log.debug("\n\n<<< LOADING ALL PROPS START >>>");
            instance.load(is);
            log.debug("PROPERTIES LIST: {}", instance.stringPropertyNames());
            log.debug("　┌──────────────────────────────────────────────────────────────────────────────────────────");
            for (String key: instance.stringPropertyNames()) {
                String value = instance.getProperty(key);
                log.debug("　│  {} = '{}'", key, value);
            }
            log.debug("　└──────────────────────────────────────────────────────────────────────────────────────────");
            log.debug("\n<<< LOADING ALL PROPS FINISHED >>>\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Properties getInstance() {
        if(instance == null) init();
        return instance;
    }

}
