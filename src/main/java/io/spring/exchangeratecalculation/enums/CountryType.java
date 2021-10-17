package io.spring.exchangeratecalculation.enums;

import lombok.Getter;

@Getter
public enum CountryType {

    KOREA("한국", "KRW"),
    JAPAN("일본", "JPY"),
    PHILIPPINE("필리핀", "PHP");

    private String name;
    private String code;

    CountryType(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
