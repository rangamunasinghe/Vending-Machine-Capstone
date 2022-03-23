package com.techelevator.view;

import java.math.BigDecimal;

public class Chip extends Product {

    public Chip(String slotNumber,String name, BigDecimal price ) {
        super(slotNumber, name, price);
    }

    public String getSoundMessage(){
        return "Crunch, Crunch, Yum!";
    }
}
