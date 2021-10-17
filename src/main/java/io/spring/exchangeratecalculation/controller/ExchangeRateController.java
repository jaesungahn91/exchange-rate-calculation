package io.spring.exchangeratecalculation.controller;

import io.spring.exchangeratecalculation.config.validation.ValidationSequence;
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
    public Object getExchangeRates(@ModelAttribute @Validated ExchangeRate.Request request) {
        ExchangeRate.Info exchangeRateInfo = exchangeRateService.getApiExchangeRates();

        double exchangeRate = Optional.ofNullable(
                exchangeRateInfo.getQuotes().get(request.getRemitCountry()+request.getReceptionCountry())
                ).orElseThrow(() -> new RuntimeException("송금, 수취 국가를 확인해주세요."));

        return new ExchangeRate.Response(exchangeRate, request);
    }

    @GetMapping("calculation")
    public Object calculateExchangeRate(@ModelAttribute @Validated(ValidationSequence.class) ExchangeRate.CalculationRequest calculationRequest) {
        ExchangeRate.Info exchangeRateInfo = exchangeRateService.getApiExchangeRates();

        double exchangeRate = Optional.ofNullable(
                exchangeRateInfo.getQuotes().get(calculationRequest.getRemitCountry()+calculationRequest.getReceptionCountry())
        ).orElseThrow(() -> new RuntimeException("송금, 수취 국가를 확인해주세요."));

        return new ExchangeRate.CalculationResponse(exchangeRate, calculationRequest);
    }
}
