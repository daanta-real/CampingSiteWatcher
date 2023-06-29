import com.google.gson.GsonBuilder;
import libraries.Props;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class D230628_01_FetchPOSTTest {

    private static Properties p;
    @BeforeAll
    public static void loadProp() {
        p = Props.getInstance();
    }

    public static String httpFetch_POST(Map<String, Object> info) {

        log.debug("┌─────────────────────────┐");
        log.debug("│ HTTP POST REQUEST START │");
        log.debug("└─────────────────────────┘");
        String result = null;

        try {

            // BASE SETTINGS
            String targetUrl = String.valueOf(info.get("targetUrl"));
            HttpURLConnection connection = (HttpURLConnection) new URI(targetUrl).toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // RESPONSE TYPE
            log.debug("1. RESPONSE TYPE PREPARING START");
            String responseType = String.valueOf(info.get("responseType"));
            switch (responseType) {
                case "json", "JSON" -> {
                    log.debug("  - RESPONSE TYPE: JSON");
                    connection.setRequestProperty("Content-Type", "application/json");
                }
                default -> log.debug("  - RESPONSE TYPE: NONE");
            }
            log.debug("1. RESPONSE TYPE PREPARING FINISHED");

            // HEADER
            log.debug("2. HEADER PREPARING START");
            Map<String, String> headerMap;
            String headerStr;
            Object headerObj = info.get("header");
            if(headerObj instanceof Map) {
                headerMap = (Map<String, String>) headerObj;
                if(!headerMap.isEmpty()) {
                    log.debug("  - HEADER CLASS: MAP");
                    for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                        String key = entry.getKey(), value = entry.getValue();
                        log.debug("  - HEADER[{}]: [{}]", key, value);
                        connection.setRequestProperty(key, value);
                    }
                }
                log.debug("  - HEADER: {}", new GsonBuilder().setPrettyPrinting().create().toJson(headerMap));
            } else if(headerObj instanceof String) {
                log.debug("  - HEADER CLASS: STRING");
                headerStr = String.valueOf(headerObj);
                log.debug("  - HEADER: {}", headerStr);
            }
            log.debug("2. HEADER PREPARING FINISHED");

            // PAYLOAD
            log.debug("3. PAYLOAD PREPARING START");
            Object payloadObj = info.get("payload");
            Map<String, String> payloadMap;
            String payloadStr = null;
            if(payloadObj instanceof Map) {
                payloadMap = (Map<String, String>) payloadObj;
                if(!payloadMap.isEmpty()) {
                    log.debug("  - PAYLOAD CLASS: MAP");
                    for (Map.Entry<String, String> entry : payloadMap.entrySet()) {
                        String key = entry.getKey(), value = entry.getValue();
                        log.debug("  - PAYLOAD[{}]: [{}]", key, value);
                        connection.setRequestProperty(key, value);
                    }
                }
                log.debug("  - PAYLOAD: {}", new GsonBuilder().setPrettyPrinting().create().toJson(payloadObj));
            } else if(payloadObj instanceof String) {
                log.debug("  - PAYLOAD CLASS: STRING");
                payloadStr = String.valueOf(headerObj);
                log.debug("  - PAYLOAD: {}", payloadStr);
            }
            log.debug("3. PAYLOAD PREPARING FINISHED");

            // SHOOT THE REQUEST
            log.debug("4. RESPONSE SHOOT START");
            if(StringUtils.isNotBlank(payloadStr)) {
                try (OutputStream outputStream = connection.getOutputStream()) {
                    outputStream.write(payloadStr.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                }
            }
            log.debug("4. RESPONSE SHOOT FINISHED");

            // GET THE RESPONSE
            log.debug("5. RESPONSE RECEIVING START");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {


                // OPEN THE RESPONSE
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // MAKE THE RESPONSE JSON
                String jsonResponse = response.toString();
                log.debug("RESPONSE(RESULT: {}", jsonResponse);
                result = jsonResponse;

            } else {
                throw new Exception(String.valueOf(responseCode));
            }
            log.debug("5. RESPONSE RECEIVING FINISHED");

            // Close the connection
            connection.disconnect();

        } catch(Exception e) {
            e.printStackTrace();
        }

        log.debug("┌───────────────────┐");
        log.debug("│ RESPONSE RECEIVED │");
        log.debug("└───────────────────┘");
        log.debug(result);
        log.debug("");
        log.debug(" - HTTP REQUEST PROCESS FINISHED - ");
        return result;

    }

    private static Map<String, String> convertStringToJson(String input) {
        Map<String, String> jsonMap = new HashMap<>();
        String[] keyValuePairs = input.split("&");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = new String(Base64.getDecoder().decode(keyValue[0]));
                String value = new String(Base64.getDecoder().decode(keyValue[1]));
                jsonMap.put(key, value);
            }
        }
        return jsonMap;
    }

    @Test
    public void testNanji() {

        String nanjiUrl = p.getProperty("nanjiUrl");
        String nanjiHeader = p.getProperty("nanjiHeader");
        String nanjiPayload = p.getProperty("nanjiPayload");
        log.debug("\n\nSTART\n\ntargetUrl: {}\nheader: {}\npayload: {}", nanjiUrl, nanjiHeader, nanjiPayload);

        Map<String, Object> info = new HashMap<>();
        info.put("targetUrl", nanjiUrl);
        info.put("header", nanjiHeader);
        info.put("payload", nanjiPayload);
        //info.put("responseType", "json");
        log.debug("\n\nCOMPLETE REQUEST:\n\n{}\n", new GsonBuilder().setPrettyPrinting().create().toJson(info));

        String result = httpFetch_POST(info);
        log.debug("result: {}", result);

    }

}
