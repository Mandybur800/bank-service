package ua.bank.bankservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyConverterDto {
    @JsonProperty("motd")
    private Object metaData;
    private boolean success;
    private Object query;
    private Object info;
    private boolean historical;
    private String date;
    private double result;
}
