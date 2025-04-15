package com.convertor.currency_convertor.scheduler;

import com.convertor.currency_convertor.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RateUpdateScheduler {

    private final CurrencyService currencyService;
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateRates() {
        log.info("Scheduled update of exchange rates started");
        currencyService.updateExchangeRates();
    }

    @Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE)
    public void updateRatesOnStartup() {
        log.info("Initial update of exchange rates on startup");
        currencyService.updateExchangeRates();
    }
}
