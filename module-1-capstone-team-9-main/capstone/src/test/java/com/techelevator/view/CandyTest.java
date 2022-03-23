package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CandyTest {
    private Candy ReesesTest;
    private Candy skittlesTest;


    @Before
    public void setUp(){
        ReesesTest = new Candy("A1", "Peanut Butter Cups", BigDecimal.valueOf(1.00));
        skittlesTest = new Candy("A2", "Skittles", BigDecimal.valueOf(0.25));
        skittlesTest.sellItem();
    }

    @Test
    public void product_available_returns_true_at_default_qty(){
        Assert.assertTrue(ReesesTest.isProductAvailable());
    }

    @Test
    public void product_available_returns_false_at_0(){
        ReesesTest.setItemQuantity(0);
        Assert.assertFalse(ReesesTest.isProductAvailable());
    }

    @Test
    public void product_available_returns_true_at_10(){
        ReesesTest.setItemQuantity(10);
        Assert.assertTrue(ReesesTest.isProductAvailable());
    }

    @Test
    public void sell_item_works(){
        ReesesTest.setItemQuantity(10);
        ReesesTest.sellItem();
        Assert.assertEquals(9,ReesesTest.getItemQuantity());
    }

    @Test
    public void get_sound_Message_returns_properly(){
        Assert.assertEquals("Munch, Munch, Yum!", ReesesTest.getSoundMessage());
    }

    @Test
    public void sell_item_compares_two_quantities(){
        Assert.assertNotEquals(ReesesTest.getItemQuantity(), skittlesTest.getItemQuantity());
    }
}
