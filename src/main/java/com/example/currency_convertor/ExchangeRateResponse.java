package com.example.currency_convertor;

//Map is used to store key-value pairs
import java.util.Map;


//This is a DTO holds and manages the data retrieved from the exchange rate API
public class ExchangeRateResponse {
    //Rates are store as Currency Code: Exchange Rate
    private Map<String, Double> rates;

    
    public Map<String, Double> getRates(){
        return rates;
    }

    public void setRates(Map<String, Double> rates){
        this.rates = rates;
    }
}
