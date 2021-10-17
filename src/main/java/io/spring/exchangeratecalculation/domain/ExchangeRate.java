package io.spring.exchangeratecalculation.domain;

import io.spring.exchangeratecalculation.util.FormatUtil;
import lombok.*;

import javax.validation.constraints.NotEmpty;
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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotEmpty(message = "송금 국가를 선택하세요.")
        private String remitCountry;
        @NotEmpty(message = "수취 국가를 선택하세요.")
        private String receptionCountry;
    }

    @Getter
    public static class CalculationRequest {
        private String remitCountry;
        private String receptionCountry;
        private String amount;
    }

    @Getter
    public static class Response {
        private String exchangeRate;
        private String name;

        public Response(double exchangeRate, Request request) {
            this.exchangeRate = FormatUtil.decimalFormat(exchangeRate);
            this.name = String.format("%s/%s", request.getReceptionCountry(), request.getRemitCountry());
        }
    }

    @Getter
    public static class CalculationResponse {

    }
}
