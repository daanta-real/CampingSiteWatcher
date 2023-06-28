import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class D230628_02_POSTTEST {
    public void test() {
        String urlString = "http://example.com/api-endpoint";

        try {
            // Create URI from the URL string
            URI uri = new URI(urlString);

            // Extract necessary components from the URI
            String protocol = uri.getScheme();
            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath();
            String query = uri.getQuery();

            // Create the connection
            HttpURLConnection connection = (HttpURLConnection) Class.forName("java.net.HttpURLConnection")
                    .getDeclaredConstructor(String.class).newInstance(protocol + "://" + host + ":" + port + path + (query != null ? "?" + query : ""));

            // Set request method
            connection.setRequestMethod("GET");

            // Get the response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Process the response
                String jsonResponse = response.toString();
                System.out.println("Response: " + jsonResponse);
            } else {
                System.out.println("Error: " + responseCode);
            }

            // Disconnect the connection
            connection.disconnect();
        } catch (IOException | URISyntaxException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
