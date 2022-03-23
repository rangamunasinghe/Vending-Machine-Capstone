package com.techelevator.view;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Product {
    private String name;
    private BigDecimal price;
    private int itemQuantity = 5;
    private String soundMessage = "This is a product, it has no sound";
    private String slotNumber;

//    Locale locale = new Locale("en", "US");
//    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    public Product(String slotNumber, String name, BigDecimal price) {
        this.name = name;
        this.slotNumber = slotNumber;
        this.price = price;
    }

    protected void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getSoundMessage() {
        return soundMessage;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public boolean isProductAvailable(){
        return this.getItemQuantity() > 0;
    }

    public void sellItem(){
        this.itemQuantity--;
    }




}
