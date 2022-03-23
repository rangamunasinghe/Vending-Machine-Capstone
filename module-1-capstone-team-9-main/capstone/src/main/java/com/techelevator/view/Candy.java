package com.techelevator.view;

import java.math.BigDecimal;

public class Candy extends Product {

    public Candy(String slotNumber,String name, BigDecimal price) {
        super(slotNumber,name, price);
    }

    public String getSoundMessage(){
        return "Munch, Munch, Yum!";
    }
}
