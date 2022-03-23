package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class GumTest {
    private Gum OrbitTest;
    private Gum FruitStripeTest;

    @Before
    public void setUp(){
        OrbitTest = new Gum("A1", "Fanta", BigDecimal.valueOf(1.00));
        FruitStripeTest = new Gum("A2", "CocaCola", BigDecimal.valueOf(0.25));
        FruitStripeTest.sellItem();
    }

    @Test
    public void product_available_returns_true_at_default_qty(){
        Assert.assertTrue(OrbitTest.isProductAvailable());
    }

    @Test
    public void product_available_returns_false_at_0(){
        OrbitTest.setItemQuantity(0);
        Assert.assertFalse(OrbitTest.isProductAvailable());
    }

    @Test
    public void product_available_returns_true_at_10(){
        OrbitTest.setItemQuantity(10);
        Assert.assertTrue(OrbitTest.isProductAvailable());
    }

    @Test
    public void sell_item_works(){
        OrbitTest.setItemQuantity(10);
        OrbitTest.sellItem();
        Assert.assertEquals(9,OrbitTest.getItemQuantity());
    }

    @Test
    public void get_sound_Message_returns_properly(){
        Assert.assertEquals("Chew, Chew, Yum!", OrbitTest.getSoundMessage());
    }

    @Test
    public void sell_item_compares_two_quantities(){
        Assert.assertNotEquals(OrbitTest.getItemQuantity(), FruitStripeTest.getItemQuantity());
    }
}
