package com.example.currency_convertor;

//Imports all the spring annotation and classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//This indicates this class handled RESTful web services.
@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    public double convert(@RequestParam String source, @RequestParam String target, @RequestParam double amount){
        double rate = currencyService.getExchangeRate(source, target);
        return amount * rate;
    }
}
