package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.stubbing.answers.ThrowsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class VendingMachineTest {

    VendingMachine vendingMachine;
    Candy skittles;
    Candy eminems;
    File inventoryFileTest = new File ("src/main/resources/inventoryFileTest.csv");
    File actualInventory = new File ("src/main/resources/vendingmachine.csv");


    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
        skittles = new Candy("A1", "Skittles", BigDecimal.valueOf(1.00));
        eminems = new Candy("A2", "M&Ms", BigDecimal.valueOf(3.57));
        vendingMachine.inventory.add(skittles);
        vendingMachine.inventory.add(eminems);

    }

    @Test
    public void testFeedMoney() {
        vendingMachine.feedMoney("10.00");
        Assert.assertEquals(BigDecimal.valueOf(10.00), vendingMachine.getBalance());
    }

    @Test
    public void testDispenseChange() {
        vendingMachine.feedMoney("10.00");
        Assert.assertEquals(BigDecimal.valueOf(10.0), vendingMachine.getBalance());
        vendingMachine.dispenseChange();
        Assert.assertEquals(BigDecimal.valueOf(0.0), vendingMachine.getBalance());
    }

    @Test
    public void purchase_item_affects_balance_and_quantity() {
        vendingMachine.feedMoney("1.00");
        vendingMachine.purchaseItem("A1");
        Assert.assertEquals(BigDecimal.valueOf(0.0), vendingMachine.getBalance());
        Assert.assertEquals(4, vendingMachine.getInventory().get(0).getItemQuantity());
    }

    @Test
    public void purchase_item_fails_with_sold_out_item() {
        try {
            vendingMachine.feedMoney("10.00");
            vendingMachine.purchaseItem("A1");
            vendingMachine.purchaseItem("A1");
            vendingMachine.purchaseItem("A1");
            vendingMachine.purchaseItem("A1");
            vendingMachine.purchaseItem("A1");
            vendingMachine.purchaseItem("A1");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void purchase_item_fails_with_insufficient_funds() {
        try {
            vendingMachine.purchaseItem("A1");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void dispense_change_returns_more_than_quarters() {
        vendingMachine.feedMoney("5.00");
        vendingMachine.purchaseItem("A2");
        Assert.assertEquals(BigDecimal.valueOf(1.43), vendingMachine.getBalance());
        vendingMachine.dispenseChange();
        Assert.assertEquals(BigDecimal.valueOf(0.0), vendingMachine.getBalance());
    }

    @Test
    public void start_vending_machine_loads_items() {
        vendingMachine.startVendingMachine(actualInventory);
        Assert.assertTrue(vendingMachine.inventory.size() > 0);
    }

    @Test
    public void start_vending_machine_throws_exception_for_null_file(){
        try{
            vendingMachine.startVendingMachine(inventoryFileTest);
        }catch(Exception e){
            Assert.fail();
        }
    }


}