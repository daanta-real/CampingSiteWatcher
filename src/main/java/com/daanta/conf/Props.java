package com.daanta.conf;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Props {

    private static Props instance;
    private Props() {
    }

    private static void init() {
        Properties propOrg = new Properties();
        try (InputStream is = Props.class.getClassLoader().getResourceAsStream("config.properties")) {
            log.debug("\n\n<<< LOADING ALL PROPS START >>>");
            propOrg.load(is);
            log.debug("PROPERTIES LIST: {}", propOrg.stringPropertyNames());
            log.debug("　┌──────────────────────────────────────────────────────────────────────────────────────────");
            for (String key: propOrg.stringPropertyNames()) {
                String value = propOrg.getProperty(key);
                log.debug("　│  {} = '{}'", key, value);
            }
            log.debug("　└──────────────────────────────────────────────────────────────────────────────────────────");
            log.debug("\n<<< LOADING ALL PROPS FINISHED >>>\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Props getInstance() {
        if(instance == null) init();
        return instance;
    }

}
