package io.spring.exchangeratecalculation.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/view/exchange-rate")
@Controller
public class ViewController {

    @GetMapping
    public String getExchangeRateView() {
        return "view";
    }
}
