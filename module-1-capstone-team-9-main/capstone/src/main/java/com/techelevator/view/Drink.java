package com.techelevator.view;

import java.math.BigDecimal;

public class Drink extends Product {
    public Drink(String slotNumber, String name, BigDecimal price ) {
        super(slotNumber,name, price);
    }

    public String getSoundMessage() {
        return "Glug, Glug, Yum!";
    }
}
