package com.example.currency_convertor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public boolean isValidCurrency(String currency) {
        // Check if the currency code is 3 letters long and contains only letters
        if (currency != null && currency.matches("^[A-Za-z]{3}$")) {
            RestTemplate restTemplate = new RestTemplate();
            String url = API_URL + "USD";  // Use a known base currency for validation

            ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
            if (response != null && response.getRates() != null) {
                // Check if the provided currency code exists in the API response rates
                return response.getRates().containsKey(currency.toUpperCase());
            }
        }
        return false;  // Invalid if it doesn't match the criteria or API response doesn't contain the currency
    }

    public double getExchangeRate(String sourceCurrency, String targetCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + sourceCurrency;
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        if (response != null && response.getRates() != null) {
            Double rate = response.getRates().get(targetCurrency);
            if (rate != null) {
                return rate;
            }
        }
        return 0;  // Return 0 if no valid rate is found
    }
}
