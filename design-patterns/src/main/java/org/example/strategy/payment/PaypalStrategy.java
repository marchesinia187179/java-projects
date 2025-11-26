package org.example.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PaypalStrategy implements PaymentStrategy {
    private String email;

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Paypal");
    }
}
