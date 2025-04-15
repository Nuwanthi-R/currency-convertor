package com.convertor.currency_convertor.service;

import com.convertor.currency_convertor.dto.request.CurrencyConversionRequestDto;
import com.convertor.currency_convertor.dto.response.CurrencyConversionResponseDto;
import com.convertor.currency_convertor.dto.response.ExchangeRateResponseDto;
import com.convertor.currency_convertor.entity.Currency;
import com.convertor.currency_convertor.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/49d662957652fcd611b05909/latest/USD";

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate;

    public CurrencyConversionResponseDto convertCurrency(CurrencyConversionRequestDto request) {
        Currency usdCurrency = currencyRepository.findByBase("USD");

        if (usdCurrency == null) {
            log.error("No exchange rates found. Please update rates first.");
            throw new RuntimeException("Exchange rates not available");
        }

        Map<String, Double> rates = usdCurrency.getRates();

        Double fromRate = rates.get(request.getFromCurrency());
        Double toRate = rates.get(request.getToCurrency());

        if (fromRate == null || toRate == null) {
            throw new RuntimeException("Currency not supported");
        }

        double exchangeRate = toRate / fromRate;
        double convertedAmount = request.getAmount() * exchangeRate;

        return new CurrencyConversionResponseDto(
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getAmount(),
                convertedAmount,
                exchangeRate
        );
    }


    public void updateExchangeRates() {
        try {
            log.info("Updating exchange rates from API");

            ExchangeRateResponseDto response = restTemplate.getForObject(API_URL, ExchangeRateResponseDto.class);

            if (response != null && response.getRates() != null) {
                Currency currency = currencyRepository.findByBase("USD");

                if (currency == null) {
                    currency = new Currency("USD", response.getRates());
                } else {
                    currency.setRates(response.getRates());
                    currency.setLastUpdated(new java.util.Date());
                }

                currencyRepository.save(currency);
                log.info("Exchange rates updated successfully");
            }
        } catch (Exception e) {
            log.error("Error updating exchange rates: " + e.getMessage(), e);
        }
    }


    public List<String> getAllCurrencies() {
        Currency currency = currencyRepository.findByBase("USD");
        if (currency != null && currency.getRates() != null) {
            return new ArrayList<>(currency.getRates().keySet());
        }
        return new ArrayList<>();
    }
}
