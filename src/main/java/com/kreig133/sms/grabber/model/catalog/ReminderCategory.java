package com.kreig133.sms.grabber.model.catalog;

/**
 * @author kreig133
 * @version 1.0
 */
public enum  ReminderCategory {
    ZERO(0), //TODO
    ONE(1),  //TODO
    TWO(2);  //TODO

    private int id;

    private ReminderCategory( int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
