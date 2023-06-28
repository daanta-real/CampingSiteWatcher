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

        return htmlContent.toString();

    }

}
