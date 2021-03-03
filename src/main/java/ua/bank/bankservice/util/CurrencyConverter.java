package ua.bank.bankservice.util;

import com.google.gson.JsonObject;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.bank.bankservice.model.Currency;

@Component
public class CurrencyConverter {
    @Autowired
    private HttpClient client;
    @Value(value = "${url.converter}")
    private String baseUrl;

    public BigDecimal convert(Currency fromCurrency, Currency toCurrency, int amount) {

        String urlStr = baseUrl + "/convert?from=" + fromCurrency.name()
                + "&to=" + toCurrency.name() + "&amount=" + amount;
        JsonObject jsonObj = client.handleRequest(urlStr);
        String result = jsonObj.get("result").getAsString();
        return new BigDecimal(result);
    }
}
