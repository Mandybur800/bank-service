package ua.bank.bankservice.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Component;
import ua.bank.bankservice.model.Currency;

@Component
public class CurrencyConverter {
    public double convert(Currency fromCurrency, Currency toCurrency, int amount) {
        String urlStr = "https://api.exchangerate.host/convert?from=" + fromCurrency.name()
                + "&to=" + toCurrency.name() + "&amount=" + amount;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonElement root = JsonParser.parseReader(new InputStreamReader(
                    (InputStream) request.getContent()));
            JsonObject jsonObj = root.getAsJsonObject();
            String result = jsonObj.get("result").getAsString();
            return Double.parseDouble(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't convert currency from:"
                    + fromCurrency + " to:" + toCurrency);
        }
    }
}
