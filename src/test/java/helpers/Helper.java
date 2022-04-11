package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Helper {

    /**
     * Convert response entity to a specific class
     * @param response
     * @param object : class that represent response structure.
     * @param <T>
     * @return specified class filled with response.
     * @throws IOException
     */
    public static <T> T convertResponseBodyToObject(HttpResponse response, Class<T> object ) throws IOException {
        return new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()),object);
    }

    /**
     * Convert json file to a specific class
     * @param jsonFilePath
     * @param object
     * @param <T>
     * @return specified class filled with json
     * @throws IOException
     */
    public static <T> T convertJsonFileToObject(String jsonFilePath,Class<T> object) throws IOException {
        return new ObjectMapper().readValue(Files.readAllBytes(Paths.get(jsonFilePath)),object);
    }

    /**
     * @param apiUrl
     * @return
     * @throws IOException
     */
    public static HttpResponse generateRequestAndExecute(String apiUrl) throws IOException {
        HttpUriRequest request = new HttpGet(apiUrl);
        return HttpClientBuilder.create().build().execute( request );
    }

    public static Properties getPropertiesFromFile(String filePath) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader(filePath));
        return prop;
    }
}
