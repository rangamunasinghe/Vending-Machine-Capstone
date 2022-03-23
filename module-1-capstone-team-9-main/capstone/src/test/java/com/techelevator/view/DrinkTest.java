package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class DrinkTest {
    private Drink FantaTest;
    private Drink cocaColaTest;

    @Before
    public void setUp(){
        FantaTest = new Drink("A1", "Fanta", BigDecimal.valueOf(1.00));
        cocaColaTest = new Drink("A2", "CocaCola", BigDecimal.valueOf(0.25));

        cocaColaTest.sellItem();
    }

    @Test
    public void product_available_returns_true_at_default_qty(){
        Assert.assertTrue(FantaTest.isProductAvailable());
    }

    @Test
    public void product_available_returns_false_at_0(){
        FantaTest.setItemQuantity(0);
        Assert.assertFalse(FantaTest.isProductAvailable());
    }

    @Test
    public void product_available_returns_true_at_10(){
        FantaTest.setItemQuantity(10);
        Assert.assertTrue(FantaTest.isProductAvailable());
    }

    @Test
    public void sell_item_works(){
        FantaTest.setItemQuantity(10);
        FantaTest.sellItem();
        Assert.assertEquals(9,FantaTest.getItemQuantity());
    }

    @Test
    public void get_sound_Message_returns_properly(){
        Assert.assertEquals("Glug, Glug, Yum!", cocaColaTest.getSoundMessage());
    }

    @Test
    public void sell_item_compares_two_quantities(){
        Assert.assertNotEquals(FantaTest.getItemQuantity(), cocaColaTest.getItemQuantity());
    }
}
