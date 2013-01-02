package com.kreig133.sms.grabber.model;

/**
 * @author kreig133
 * @version 1.0
 */
public interface Request {
    String getURI();

    public static class Factory{
        public static Request build( RawParseInfo parseInfo ) {
            Transaction transaction = new Transaction();

            return transaction;
        }
    }
}
