package ua.bank.bankservice.model.dto;

import lombok.Data;

@Data
public class Query {
    private String from;
    private String to;
    private Double amount;
}
