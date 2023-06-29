package com.daanta.conf;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropsCommon {

    private static Properties instance;
    private PropsCommon() {
    }

    public static Properties getInstance() {
        if(instance != null) return instance;
        instance = new Properties();
        try (InputStream is = PropsCommon.class.getClassLoader().getResourceAsStream("config.properties")) {
            log.debug("\n\n<<< LOADING ALL PROPS START >>>");
            instance.load(is);
            log.debug("PROPERTIES LIST: {}", instance.stringPropertyNames());
            log.debug("　┌──────────────────────────────────────────────────────────────────────────────────────────");
            for (String key : instance.stringPropertyNames()) {
                String value = instance.getProperty(key);
                log.debug("　│  {} = '{}'", key, value);
            }
            log.debug("　└──────────────────────────────────────────────────────────────────────────────────────────");
            log.debug("\n<<< LOADING ALL PROPS FINISHED >>>\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

}
