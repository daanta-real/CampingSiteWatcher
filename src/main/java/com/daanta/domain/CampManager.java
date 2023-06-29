package com.daanta.domain;

import com.daanta.conf.PropsCommon;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

// Manage domain of all

@Slf4j
public class CampManager {

    private static Camp nanji;

    public static Map<String, Object> initializeCampOne(String campName) {
        
        // Prepare property values
        log.debug("\n\n<<< LOADING PROPS OF {} START >>>", campName);
        Properties p = PropsCommon.getInstance();
        String url = p.getProperty(campName + "Url");
        String headerStr = p.getProperty(campName + "Header");
        String bodyStr = p.getProperty(campName + "Body");
        log.debug("\n  - url: {}\n  - header: {}\n  - body: {}", url, headerStr, bodyStr);

        // Header's String separators are: '|' for params, and ',' for key and values
        log.debug("PREPARING HEADERS..");
        String[] headerArr = headerStr.split("\\|");
        Map<String, String> header = new HashMap<>();
        for(String s: headerArr) {
            String[] headerOne = s.split(":");
            String key = headerOne[0], value = headerOne[1];
            log.debug("  ├ HEADER key:[{}] - value:[{}]", key, value);
            header.put(key, value);
        }
        log.debug("  └ FIN.");

        // body's String separators are: '&' for params, and '=' for key and values
        log.debug("PREPARING BODY..");
        String[] bodyArr = bodyStr.split("&");
        Map<String, String> body = new HashMap<>();
        for(String s: bodyArr) {
            String[] bodyOne = s.split("=");
            String key = bodyOne[0], value = bodyOne[1];
            log.debug("  ├ BODY key:[{}] - value:[{}]", key, value);
            body.put(key, value);
        }
        log.debug("  └ FIN.");

        log.debug("　┌──────────────────────────────────────────────────────────────────────────────────────────");
        log.debug("　│ ◆ CAPMING SITE \"{}\" REQUEST INFO ◆", campName);
        log.debug("　│   - URL: {}", url);
        log.debug("　│   - HEADER: {}", header);
        log.debug("　│   - BODY: {}", body);
        log.debug("　└──────────────────────────────────────────────────────────────────────────────────────────");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("url", url);
        resultMap.put("header", header);
        resultMap.put("body", body);

        log.debug("\n<<< LOADING PROPS OF {} FINISHED >>>\n", campName);
        return resultMap;

    }

    public static void initializeCampAll() {
        nanji = Camp.builder().campName("nanji").build();
    }

    // Get Camp instance with the name what user want.
    public static Camp getCamp(String campName) throws Exception {
        return switch(campName) {
            case "nanji": yield nanji;
            default: throw new Exception();
        };
    }

}
