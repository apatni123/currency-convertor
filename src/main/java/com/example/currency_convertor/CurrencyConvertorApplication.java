//Sets up the main application class which runs the spring boot appplication

//Package organises a set of related classes and interfaces. Same as file its in
package com.example.currency_convertor;

//Imports the thing that launches the application that's why you can do .run
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Calling the setting 
@SpringBootApplication
public class CurrencyConvertorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConvertorApplication.class, args);
	}

}
