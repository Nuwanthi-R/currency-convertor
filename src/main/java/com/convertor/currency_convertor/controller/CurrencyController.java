package com.convertor.currency_convertor.controller;

import com.convertor.currency_convertor.service.CurrencyService;
import com.convertor.currency_convertor.dto.request.CurrencyConversionRequestDto;
import com.convertor.currency_convertor.dto.response.CurrencyConversionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("/convert")
    public ResponseEntity<CurrencyConversionResponseDto> convertCurrency(@RequestBody CurrencyConversionRequestDto request) {
        CurrencyConversionResponseDto response = currencyService.convertCurrency(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> getAllCurrencies() {
        List<String> currencies = currencyService.getAllCurrencies();
        return ResponseEntity.ok(currencies);
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateRates() {
        currencyService.updateExchangeRates();
        return ResponseEntity.ok("Exchange rates updated successfully");
    }
}
