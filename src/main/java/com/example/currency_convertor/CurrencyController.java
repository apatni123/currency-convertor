package com.example.currency_convertor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;

@Controller
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("currencyForm", new CurrencyForm());
        return "currency_form";
    }
    
    

    @GetMapping("/convert")
    public String convert(@ModelAttribute("currencyForm") @Valid CurrencyForm currencyForm, BindingResult bindingResult, Model model) {

        String source = currencyForm.getSource().toUpperCase();
        String target = currencyForm.getTarget().toUpperCase();
        Double amount = currencyForm.getAmount();

        // Validate the source and target currencies
        if (!currencyService.isValidCurrency(source) && !source.isBlank()) {
            bindingResult.rejectValue("source", "currency.invalid", "This is not a valid code. Please try again!");
        }
        if (!currencyService.isValidCurrency(target)) {
            bindingResult.rejectValue("target", "currency.invalid", "This is not a valid code. Please try again!");
        }
        if (source.equalsIgnoreCase(target)) {
            bindingResult.rejectValue("target","currency.invalid", "Source code and Target code cannot be the same");
        }

        // If validation errors exist, return to the form page
        if (bindingResult.hasErrors()) {
            return "currency_form";
        }

        // Proceed with conversion if both currencies are valid
        try {
            double rate = currencyService.getExchangeRate(source, target);
            double convertedAmount = amount * rate;
        
            
            BigDecimal roundedAmount = BigDecimal.valueOf(convertedAmount)
                                                  .setScale(2, RoundingMode.HALF_UP);
            convertedAmount = roundedAmount.doubleValue();
        
            
            String formattedAmount = String.format("%.2f", convertedAmount);
            
            model.addAttribute("convertedAmount", formattedAmount);
        } catch (Exception e) {
            model.addAttribute("error", "There was an error converting the currency.");
            return "currency_form";
        }
        

        return "currency_form";
    }
}
