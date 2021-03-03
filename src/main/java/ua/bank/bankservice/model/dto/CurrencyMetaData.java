package ua.bank.bankservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyMetaData {
    @JsonProperty("msg")
    private String message;
    private String url;
}
