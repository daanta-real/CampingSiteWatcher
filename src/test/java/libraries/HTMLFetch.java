package libraries;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

// HTML Scrapping test

@Slf4j
public class HTMLFetch {

    public static String httpFetch_GET(String targetURL) {

        log.debug("▶▶▶ FETCH START: {}", targetURL);

        StringBuilder htmlContent = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            URL url = new URI(targetURL).toURL();
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                htmlContent.append(line);
                htmlContent.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String result = htmlContent.toString();

        log.debug("▶▶▶ FETCH FINISHED. LENGTH: {}", result.length());
        return result;

    }

//    public static String httpFetch_POST(String targetURL, Map<String, Object> info) {
//
//        String result = null;
//
//        try {
//
//            // BASE SETTINGS
//            URI url = new URI(targetURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//
//            // RESPONSE TYPE
//            String responseType = null;
//            responseType = String.valueOf(info.get("responseType"));
//            String contentType = switch(responseType) {
//                case "json", "JSON": yield "application/json";
//                default: yield null;
//            };
//            if(StringUtils.isNotBlank(contentType)) {
//                connection.setRequestProperty("Content-Type", contentType);
//            }
//
//            // HEADER
//            log.debug("HEADER START");
//            Map<String, String> header = (Map<String, String>) info.get("header");
//            for (Map.Entry<String, String> entry : header.entrySet()) {
//                String key = entry.getKey(), value = entry.getValue();
//                log.debug("  - HEADER [{}]: [{}]", key, value);
//                connection.setRequestProperty(key, value);
//            }
//            log.debug("HEADER FINISHED");
//
//            // PAYLOAD
//            log.debug("PAYLOAD START");
//            Map<String, String> payloadObj = (Map<String, String>) info.get("payload");
//            String payload = new GsonBuilder().setPrettyPrinting().create().toJson(payloadObj);
//            log.debug("\n\nPAYLOAD:\n\n{}\n", payload);
//            log.debug("PAYLOAD FINISHED");
//
//            // SHOOT THE REQUEST
//            try (OutputStream outputStream = connection.getOutputStream()) {
//                outputStream.write(payload.getBytes("UTF-8"));
//                outputStream.flush();
//            }
//
//            // GET THE RESPONSE
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//
//                // OPEN THE RESPONSE
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//                reader.close();
//
//                // MAKE THE RESPONSE JSON
//                String jsonResponse = response.toString();
//                log.debug("RESPONSE(RESULT: {}", jsonResponse);
//                result = jsonResponse;
//
//            } else {
//                throw new Exception(String.valueOf(responseCode));
//            }
//
//            // Close the connection
//            connection.disconnect();
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//
//    }

}
