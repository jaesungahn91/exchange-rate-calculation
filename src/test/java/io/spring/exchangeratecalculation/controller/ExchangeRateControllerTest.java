package io.spring.exchangeratecalculation.controller;

import io.spring.exchangeratecalculation.ExchangeRateCalculationApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ExchangeRateCalculationApplication.class)
@ActiveProfiles
@ExtendWith({SpringExtension.class})
class ExchangeRateControllerTest {

    private final String BASE_URL = "http://localhost/api/exchange-rate";

    protected MockMvc mockMvc;

    @BeforeEach
    protected void setUp(WebApplicationContext webAppContext){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
    }

    @Test
    void getCountries() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/countries"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getExchangeRates() throws Exception {
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("remitCountry", "USD");
        info.add("receptionCountry", "KRW");

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL).params(info))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void calculateExchangeRate() throws Exception {
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("remitCountry", "USD");
        info.add("receptionCountry", "KRW");
        info.add("amount", "100");

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/calculation").params(info))
                .andExpect(status().isOk())
                .andDo(print());
    }
}