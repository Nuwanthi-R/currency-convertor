package com.convertor.currency_convertor.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionRequestDto {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
}
