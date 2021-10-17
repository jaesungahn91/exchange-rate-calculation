package io.spring.exchangeratecalculation.controller;

import io.spring.exchangeratecalculation.domain.Country;
import io.spring.exchangeratecalculation.domain.ExchangeRate;
import io.spring.exchangeratecalculation.enums.CountryType;
import io.spring.exchangeratecalculation.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/exchange-rate")
@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("countries")
    public Object getCountries() {
        return Arrays.stream(CountryType.values())
                .map(Country.Response::new)
                .collect(Collectors.toList());
    }

    @GetMapping
    public Object getExchangeRate(@RequestBody @Validated ExchangeRate.Request request) {
        ExchangeRate.Info exchangeRateInfo = exchangeRateService.getApiExchangeRate();

        double exchangeRate = Optional.ofNullable(
                exchangeRateInfo.getQuotes().get(request.getRemitCountry()+request.getReceptionCountry())
                ).orElseThrow(() -> new RuntimeException("존재하지 않는 환율입니다."));

        return new ExchangeRate.Response(exchangeRate, request);
    }
}
