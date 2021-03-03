package ua.bank.bankservice.model.dto;

import lombok.Data;

@Data
public class CurrencyConverterDto {
    private Object motd;
    private boolean success;
    private Object query;
    private Object info;
    private boolean historical;
    private String date;
    private double result;
}
