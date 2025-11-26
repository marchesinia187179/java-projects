package org.example.strategy;

public class StrategyMain {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item bread = new Item("123", 0.44);
        Item milk = new Item("23a", 1.2);

        cart.addItem(bread);
        cart.addItem(milk);

        cart.pay(new CreditCardStrategy("1234-5678-9012-3456"));

        cart.removeItem(bread);
        cart.pay(new PaypalStrategy("mikymouse@gmail.com"));
    }
}
