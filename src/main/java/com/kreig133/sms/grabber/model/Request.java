package com.kreig133.sms.grabber.model;

/**
 * @author kreig133
 * @version 1.0
 */
public interface Request {
    public String getURI();

    public static class Factory{
        public static Request build( RawParseInfo string ) {
            return new Transaction();//TODO
        }
    }
}
