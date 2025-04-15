package com.convertor.currency_convertor.dto.response;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionResponseDto {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double convertedAmount;
    private double exchangeRate;

}

