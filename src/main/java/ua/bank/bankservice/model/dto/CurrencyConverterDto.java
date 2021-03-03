package ua.bank.bankservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyConverterDto {
    @JsonProperty("motd")
    private CurrencyMetaData metaData;
    private boolean success;
    private Query query;
    private Info info;
    private boolean historical;
    private String date;
    private double result;
}
