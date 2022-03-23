package com.techelevator.view;

public class InsufficientFundsException extends Exception{

    String message = "\nYou have insufficient funds for this transaction!\n" +
            "Vend-O-Matic is hungry for more money! Feed me!\n";
    public InsufficientFundsException() {

    }

    @Override
    public String getMessage() {
        return message;
    }
}
