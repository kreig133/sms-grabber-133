package com.kreig133.sms.grabber.model.catalog;

/**
 * @author kreig133
 * @version 1.0
 */
public enum Currency {
    RUR(2), EUR(3), USD(1);

    int id;

    private Currency( int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}
