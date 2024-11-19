package com.example.currency_convertor;

//Imports all the spring annotation and classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



//This indicates this class handles HTTP requests and sends back the response
//Also sends the response as a JSON object

@Controller
public class CurrencyController {


    //Finds a matching bean and injects it into class 
    //Finds the CurrencyService bean which is a spring-managed bean without created a new object
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/")
    public String showForm() {
        //Basically says return the currency form which is in the template. This is called a view
        return "currency_form";
    }

    //This specifies that this method handles HTTP get requests mad to the /convert URL
    @GetMapping("/convert")

    //@RequestParams binds the query parameters from the get request to the method parameteers
    public String convert(@RequestParam String source, @RequestParam String target, @RequestParam double amount, Model model){

        source = source.toUpperCase();
        target = target.toUpperCase();

        
        //then its basically calling the get exchange rate method from currency sercue
        double rate = currencyService.getExchangeRate(source, target);
        double convertedAmount =  amount * rate;

        //Passed to the view
        model.addAttribute("convertedAmount", convertedAmount);
        return ("currency_form");
    }
}
