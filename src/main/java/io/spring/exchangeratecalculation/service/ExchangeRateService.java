package io.spring.exchangeratecalculation.service;

import io.spring.exchangeratecalculation.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExchangeRateService {

    private final String BASE_URL = "http://www.apilayer.net";
    private final String URI_STRING = "api/live";
    private final String QUERY_PARAM = "access_key";
    private final String ACCESS_KEY = "de10df8941db13a69c5ddb6202c1bf09";

    private final WebClient webClient;

    @Cacheable(value = "exchangeRatesCache")
    public ExchangeRate.Info getApiExchangeRate() {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(BASE_URL);
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        URI uri = uriBuilderFactory.uriString(URI_STRING)
                .queryParam(QUERY_PARAM, ACCESS_KEY)
                .build();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ExchangeRate.Info.class)
                .blockOptional().orElse(new ExchangeRate.Info());
    }
}
