package com.kreig133.sms.grabber.model.catalog;

/**
 * @author kreig133
 * @version 1.0
 */
public enum AccountType {
    CCARD( "ccard"), CASH("cash");

    private String name;

    private AccountType( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
