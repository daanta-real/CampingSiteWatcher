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

        String result = null;

        try {

            // BASE SETTINGS
            String targetUrl = String.valueOf(info.get("targetUrl"));
            HttpURLConnection connection = (HttpURLConnection) new URI(targetUrl).toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // RESPONSE TYPE
            String responseType = String.valueOf(info.get("responseType"));
            String contentType = switch(responseType) {
                case "json", "JSON": yield "application/json";
                default: yield null;
            };
            if(StringUtils.isNotBlank(contentType)) {
                connection.setRequestProperty("Content-Type", contentType);
            }

            // HEADER
            log.debug("HEADER START");
            Map<String, String> header = (Map<String, String>) info.get("header");
            if(header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    String key = entry.getKey(), value = entry.getValue();
                    log.debug("  - HEADER [{}]: [{}]", key, value);
                    connection.setRequestProperty(key, value);
                }
            }
            log.debug("HEADER FINISHED");

            // PAYLOAD
            log.debug("PAYLOAD START");
            Map<String, String> payloadObj = (Map<String, String>) info.get("payload");
            String payload = new GsonBuilder().setPrettyPrinting().create().toJson(payloadObj);
            log.debug("\n\nPAYLOAD:\n\n{}\n", payload);
            log.debug("PAYLOAD FINISHED");

            // SHOOT THE REQUEST
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(payload.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }

            // GET THE RESPONSE
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

            // Close the connection
            connection.disconnect();

        } catch(Exception e) {
            e.printStackTrace();
        }

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
    public void test1() {

        String targetUrl = p.getProperty("nanjiUrl");
        String nanjiOption = p.getProperty("nanjiOption");
        Map<String, String> payload = convertStringToJson(nanjiOption);

        log.debug("\n\nSTART\n\ntargetUrl: {}\nnanjiOption: {}\npayload: {}", targetUrl, nanjiOption, payload);

        Map<String, Object> info = new HashMap<>();
        info.put("targetUrl", targetUrl);
        info.put("payload", payload);
        info.put("responseType", "json");
        log.debug("\n\nCOMPLETE REQUEST:\n\n{}\n", new GsonBuilder().setPrettyPrinting().create().toJson(info));

        String result = httpFetch_POST(info);
        log.debug("result: {}", result);

    }

}
