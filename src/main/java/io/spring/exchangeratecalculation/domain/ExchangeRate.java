package io.spring.exchangeratecalculation.domain;

import io.spring.exchangeratecalculation.config.validation.ValidationGroups;
import io.spring.exchangeratecalculation.util.FormatUtil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Map;

@Getter
public class ExchangeRate {

    @Getter
    public static class Info {
        private String success;
        private String terms;
        private String privacy;
        private String timestamp;
        private String source;
        private Map<String, Double> quotes;
    }

    @Setter
    @Getter
    public static class Request {
        @NotEmpty(message = "송금 국가를 선택하세요.")
        private String remitCountry;
        @NotEmpty(message = "수취 국가를 선택하세요.")
        private String receptionCountry;
    }

    @Setter
    @Getter
    public static class CalculationRequest {
        @NotEmpty(message = "송금 국가를 선택하세요.")
        private String remitCountry;
        @NotEmpty(message = "수취 국가를 선택하세요.")
        private String receptionCountry;
        @NotNull(message = "송금액이 바르지 않습니다.", groups = ValidationGroups.NotEmptyGroup.class)
        @Min(value = 1, message = "송금액이 바르지 않습니다.", groups = ValidationGroups.PatternCheckGroup.class)
        @Max(value = 10000, message = "송금액이 바르지 않습니다.", groups = ValidationGroups.PatternCheckGroup.class)
        private Integer amount;
    }

    @Getter
    public static class Response {
        private String exchangeRate;
        private String currency;

        public Response(double exchangeRate, Request request) {
            this.exchangeRate = FormatUtil.decimalFormat(exchangeRate);
            this.currency = String.format("%s/%s", request.getReceptionCountry(), request.getRemitCountry());
        }
    }

    @Getter
    public static class CalculationResponse {
        private String amount;
        private String currency;

        public CalculationResponse(double exchangeRate, CalculationRequest calculationRequest) {
            this.amount = FormatUtil.decimalFormat(exchangeRate * calculationRequest.getAmount());
            this.currency = calculationRequest.getReceptionCountry();
        }
    }
}
