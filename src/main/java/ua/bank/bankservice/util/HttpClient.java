package ua.bank.bankservice.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HttpClient {
    public JsonObject handleRequest(String urlStr) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(new HttpGet(urlStr))) {
            HttpEntity entity = response.getEntity();
            JsonElement root = JsonParser.parseString(EntityUtils.toString(entity));
            return root.getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Can't execute request:" + urlStr);
        }
    }
}
