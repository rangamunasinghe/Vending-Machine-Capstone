package com.techelevator.view;

import java.math.BigDecimal;

public class Gum extends Product {
    public Gum( String slotNumber, String name, BigDecimal price) {
        super(slotNumber,name, price);
    }

    public String getSoundMessage() {
        return "Chew, Chew, Yum!";
    }
}
