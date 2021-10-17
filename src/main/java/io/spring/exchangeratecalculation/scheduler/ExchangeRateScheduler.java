package io.spring.exchangeratecalculation.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ExchangeRateScheduler {

    @CacheEvict(value = "exchangeRatesCache")
    @Scheduled(cron = "0 0 0 * * *")
    public void evictExchangeRateCache() {
        log.info("EvictExchangeRateCache [{}]", LocalDateTime.now());
    }
}