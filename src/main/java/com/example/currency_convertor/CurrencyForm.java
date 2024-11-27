package com.example.currency_convertor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CurrencyForm {

    @NotNull(message = "Source currency is required")
    private String source;

    @NotNull(message = "Target currency is required")
    private String target;

    @Positive(message = "Amount must be greater than zero")
    private double amount;

    // Getters and Setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
