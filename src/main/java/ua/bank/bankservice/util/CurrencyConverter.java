package ua.bank.bankservice.util;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.bank.bankservice.model.Currency;
import ua.bank.bankservice.model.dto.CurrencyConverterDto;

@Component
public class CurrencyConverter {
    private HttpClient client;
    @Value(value = "${url.converter}")
    private String baseUrl;

    @Autowired
    public CurrencyConverter(HttpClient client) {
        this.client = client;
    }

    public BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount) {
        String urlStr = String.format("%s/convert?from=%s&to=%s&amount=%s",baseUrl,
                fromCurrency.name(), toCurrency.name(), amount);
        CurrencyConverterDto currencyConverterDto = client.handleRequest(urlStr,
                CurrencyConverterDto.class);
        return BigDecimal.valueOf(currencyConverterDto.getResult());
    }
}
