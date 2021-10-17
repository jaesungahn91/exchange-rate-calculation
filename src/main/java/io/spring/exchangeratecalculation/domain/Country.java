package io.spring.exchangeratecalculation.domain;

import io.spring.exchangeratecalculation.enums.CountryType;
import lombok.Getter;

public class Country {

    @Getter
    public static class Response {
        private String name;
        private String code;

        public Response(CountryType countryType) {
            this.name = String.format("%s(%s)", countryType.getName(), countryType.getCode());
            this.code = countryType.getCode();
        }
    }
}
