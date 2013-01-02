package com.kreig133.sms.grabber.model;

/**
 * @author kreig133
 * @version 1.0
 */
public class RawParseInfo {
    public static final String CARD_NUMBER= "cardNumber";
    public static final String DATE = "date" ;
    public static final String VALUE = "value";
    public static final String CURRENCY = "currency";
    public static final String PAYEE = "payee";

    public Operation operation;
    public String cardNumber;
    public String date;
    public String value;
    public String currency;
    public String payee;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append( "RawParseInfo" );
        sb.append( "{operation=" ).append( operation );
        sb.append( ", cardNumber='" ).append( cardNumber ).append( '\'' );
        sb.append( ", date='" ).append( date ).append( '\'' );
        sb.append( ", value='" ).append( value ).append( '\'' );
        sb.append( ", currency='" ).append( currency ).append( '\'' );
        sb.append( ", payee='" ).append( payee ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }
}
