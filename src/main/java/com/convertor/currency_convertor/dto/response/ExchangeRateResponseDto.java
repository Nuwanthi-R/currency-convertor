package com.convertor.currency_convertor.dto.response;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponseDto {
    private String base;
    private String date;
    private Map<String, Double> rates;

}