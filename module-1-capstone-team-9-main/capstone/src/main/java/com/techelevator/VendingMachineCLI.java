package com.techelevator;

import com.techelevator.view.Logger;
import com.techelevator.view.Menu;
import com.techelevator.view.Product;
import com.techelevator.view.VendingMachine;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static com.techelevator.view.VendingMachine.currencyFormat;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
    private static final String ONE_DOLLAR_BILL = ("1.00");
    private static final String TWO_DOLLAR_BILL = ("2.00");
    private static final String FIVE_DOLLAR_BILL = ("5.00");
    private static final String TEN_DOLLAR_BILL = ("10.00");
    private static final String[] DOLLAR_AMOUNT_OPTIONS = {ONE_DOLLAR_BILL, TWO_DOLLAR_BILL, FIVE_DOLLAR_BILL, TEN_DOLLAR_BILL};
    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;

    }

    public void run() {

        File inventoryFile = new File("src/main/resources/vendingmachine.csv");
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.startVendingMachine(inventoryFile);
        Logger logger = new Logger();

        while (true) {
            System.out.println("Welcome to the Vend-O-Matic 801!  ");
            System.out.print("Current balance: " + currencyFormat(vendingMachine.getBalance()) +
                    "\nWhat would you like to do?\n");
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                List<String> itemChoiceList = createProductChoiceList(vendingMachine.getInventory());
                String[] displayItems = itemChoiceList.toArray(new String[0]);
                menu.displayMenuOptions(displayItems);
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                System.out.println("Current balance: " + currencyFormat(vendingMachine.getBalance()));
                String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                    System.out.println("Select how much money you wish to add. ");
                    menu.feedMoneyMenuOptions(DOLLAR_AMOUNT_OPTIONS);
                    String dollarAmount = (String) menu.getChoiceFromUserInput(DOLLAR_AMOUNT_OPTIONS);
                    BigDecimal balanceBeforeFeed = vendingMachine.getBalance();
                    vendingMachine.feedMoney(dollarAmount);
                    logger.logToFile("FEED MONEY: ", balanceBeforeFeed, vendingMachine.getBalance());

                } else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                    List<String> displayChoicesList = createProductChoiceList(vendingMachine.getInventory());
                    menu.displayMenuOptions(displayChoicesList.toArray(new String[0]));
                    String productChoice = (String) menu.getChoiceFromUserInput(createSlotNumbers(vendingMachine.getInventory()));
                    BigDecimal balanceBeforePurchase = vendingMachine.getBalance();
                    String productName = createLoggerEntryName(productChoice, vendingMachine);
                    vendingMachine.purchaseItem(productChoice);
                    logger.logToFile(productName, balanceBeforePurchase, vendingMachine.getBalance());

                    // do purchase
                } else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                    BigDecimal balanceBeforeChange = vendingMachine.getBalance();
                    vendingMachine.dispenseChange();
                    logger.logToFile("GIVE CHANGE: ", balanceBeforeChange, vendingMachine.getBalance());
                }
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                BigDecimal balanceBeforeChange = vendingMachine.getBalance();
                vendingMachine.dispenseChange();
                logger.logToFile("GIVE CHANGE: ", balanceBeforeChange, vendingMachine.getBalance());
            }


        }

    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }

    public static List<String> createProductChoiceList(List<Product> inventory) {
        List<String> itemChoiceList = new ArrayList<>();

        for (Product product : inventory) {

            String displayItem = product.getSlotNumber();
            displayItem += " ";
            displayItem += product.getName();
            displayItem += " Price: ";
            displayItem += currencyFormat(product.getPrice());
            displayItem += ", Qty: ";

            if (!product.isProductAvailable()) {
                displayItem += "SOLD OUT!!";
            } else {
                displayItem += product.getItemQuantity();
            }
            itemChoiceList.add(displayItem);
        }
        return itemChoiceList;
    }

    public static String[] createSlotNumbers(List<Product> inventory) {

        List<String> slotNumbersList = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++) {
            String slotNumber = inventory.get(i).getSlotNumber();
            slotNumbersList.add(slotNumber);
        }
        return slotNumbersList.toArray(new String[0]);
    }

    public static String createLoggerEntryName(String productChoice, VendingMachine vendingMachine) {

        String productName = "";
        for (Product item : vendingMachine.getInventory()) {
            if (productChoice.equals(item.getSlotNumber())) {
                productName = item.getName();
            }
        }
        return productName + " " + productChoice;
    }
}
