package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    List<Product> inventory = new ArrayList<>();
    BigDecimal balance = BigDecimal.valueOf(0.00);

    public List<Product> getInventory() {
        return inventory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    //    Startup method: Reads CSV and restocks. Contains Try/catch- FileNotFound -STILL NEEDS TEST
    public void startVendingMachine(File inventoryFile) {
        try (Scanner fileInput = new Scanner(inventoryFile)) {
            while (fileInput.hasNextLine()) {
                String currentLine = fileInput.nextLine();
                String[] productProperties = currentLine.split("\\|");
                double doublePrice = Double.parseDouble(productProperties[2]);
                if (productProperties[3].equalsIgnoreCase("Chip")) {
                    inventory.add(new Chip(productProperties[0], productProperties[1], BigDecimal.valueOf(doublePrice)));
                }
                if (productProperties[3].equalsIgnoreCase("Drink")) {
                    inventory.add(new Drink(productProperties[0], productProperties[1], BigDecimal.valueOf(doublePrice)));
                }
                if (productProperties[3].equalsIgnoreCase("Gum")) {
                    inventory.add(new Gum(productProperties[0], productProperties[1], BigDecimal.valueOf(doublePrice)));
                }
                if (productProperties[3].equalsIgnoreCase("Candy")) {
                    inventory.add(new Candy(productProperties[0], productProperties[1], BigDecimal.valueOf(doublePrice)));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        }
    }

    //    feed money method - interacts with balance
    public void feedMoney(String userInput) {
        double inputDouble = Double.parseDouble(userInput);
        balance = balance.add(BigDecimal.valueOf(inputDouble));

    }

    //    dispense change method - interacts with balance
    public void dispenseChange() {
        BigDecimal quarter = BigDecimal.valueOf(0.25);
        BigDecimal dime = BigDecimal.valueOf(0.10);
        BigDecimal nickel = BigDecimal.valueOf(0.05);
        BigDecimal penny = BigDecimal.valueOf(0.01);
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        int pennyCount = 0;
        BigDecimal returnChange = balance;

        while (balance.compareTo(BigDecimal.valueOf(0.00)) > 0) {

            if (balance.compareTo(quarter) >= 0) {
                balance = balance.subtract(quarter);
                quarterCount++;
            }
            if (balance.compareTo(quarter) < 0 &&
                    balance.compareTo(dime) >= 0) {
                balance = balance.subtract(dime);
                dimeCount++;
            }
            if (balance.compareTo(dime) < 0 &&
                    balance.compareTo(nickel) >= 0) {
                balance = balance.subtract(nickel);
                nickelCount++;
            }
            if (balance.compareTo(nickel) < 0 &&
                    balance.compareTo(penny) >= 0) {
                balance = balance.subtract(penny);
                pennyCount++;
            }
        }
        String change = "\nYour change is " + currencyFormat(returnChange) + ", you should receive ";
        if (quarterCount > 1) {
            change += quarterCount + " quarters ";
        } else if (quarterCount == 1) {
            change += quarterCount + " quarter ";
        }
        if (dimeCount > 1) {
            change += dimeCount + " dimes ";
        } else if (dimeCount == 1) {
            change += dimeCount + " dime ";
        }
        if (nickelCount > 1) {
            change += nickelCount + " nickels ";
        } else if (nickelCount == 1) {
            change += nickelCount + " nickel ";
        }
        if (pennyCount > 1) {
            change += pennyCount + " pennies ";
        } else if (pennyCount == 1) {
            change += pennyCount + " penny";
        }
//        System.out.println("\nYour change is " + currencyFormat(returnChange) + ", you should receive " + quarterCount + " quarters, " +
//                dimeCount + " dimes, " + nickelCount + " nickels, " + pennyCount + " pennies. ");
        System.out.println(change);
        balance = BigDecimal.valueOf(0.00);
    }

    //    select product (purchase) - interacts with inventory AND balance
    public void purchaseItem(String userInput) {

        try {
            for (Product item : inventory) {
                if (userInput.equalsIgnoreCase(item.getSlotNumber())) {
                    if (balance.compareTo(item.getPrice()) < 0) {
                        throw new InsufficientFundsException();
                    }
                    if (!item.isProductAvailable()) {
                        throw new Exception();
                    }
                    dispenseProduct(item);
                    balance = balance.subtract(item.getPrice());
                    item.sellItem();
                }

            }
        } catch (InsufficientFundsException ife) {
            System.out.println(ife.getMessage());
        } catch (Exception e) {
            System.out.println("\nSOLD OUT!! This item is not available!!\n");
        } // create custom exceptions and messages for insufficient funds and wrong slot number
    }


//    dispense (getSoundMessage)

    public void dispenseProduct(Product product) {
        System.out.print("\nEnjoy your " + product.getName() + "!  ");
        System.out.println(product.getSoundMessage());
        System.out.println("\n\n");
    }

    public static String currencyFormat(BigDecimal num) {
        return NumberFormat.getCurrencyInstance().format(num);
    }

}


