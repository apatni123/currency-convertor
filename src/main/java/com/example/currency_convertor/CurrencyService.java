//This sets up API integration

//Package organises a set of related classes and interfaces. Same as file its in
package com.example.currency_convertor;


//This imports the thing that says the class is managed by spring
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


//This basically marks this class as a service bean. Service beans hold business logic, like interacting with external APIS
@Service
public class CurrencyService {
    //static means this variable belongs to the class and not an object
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public double getExchangeRate (String sourceCurrency, String targetCurrency){
        //this is what makes HTTP requests. in this case used to GET
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + sourceCurrency;

        //This basically is making the request and mapping the response as an instance of exchange rate
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        //This is saying that if the response worked and the rate exists 
        if (response != null && response.getRates() != null){
            //Then this retrieves the exchange rate for the target currency from the rates map
            return response.getRates().get(targetCurrency);
        }
        return 0;
    }
}
