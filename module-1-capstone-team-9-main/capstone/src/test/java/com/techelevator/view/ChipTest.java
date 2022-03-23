package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ChipTest {


    Chip ruffles;
    Chip cheetos;

    @Before
    public void setUp() {
        ruffles = new Chip("D4", "Ruffles", BigDecimal.valueOf(0.50));
        cheetos = new Chip("D3", "Cheetos",BigDecimal.valueOf(0.75));
        cheetos.sellItem();
    }


    @Test
    public void product_available_returns_true_at_default_qty(){
        Assert.assertTrue(ruffles.isProductAvailable());
    }

    @Test
    public void product_available_returns_false_at_0(){
        ruffles.setItemQuantity(0);
        Assert.assertFalse(ruffles.isProductAvailable());
    }

    @Test
    public void product_available_returns_true_at_10(){
        ruffles.setItemQuantity(10);
        Assert.assertTrue(ruffles.isProductAvailable());
    }

    @Test
    public void sell_item_works(){
        Assert.assertNotEquals(cheetos.getItemQuantity(), ruffles.getItemQuantity());
    }


    @Test
    public void get_sound_Message_returns_properly(){
        Assert.assertEquals("Crunch, Crunch, Yum!", cheetos.getSoundMessage());
    }


}
