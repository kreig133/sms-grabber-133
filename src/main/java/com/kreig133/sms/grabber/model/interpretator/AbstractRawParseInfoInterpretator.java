package com.kreig133.sms.grabber.model.interpretator;

import com.kreig133.sms.grabber.model.RawParseInfoInterpretator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kreig133
 * @version 1.0
 */
public abstract class AbstractRawParseInfoInterpretator implements RawParseInfoInterpretator {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );

    public static Date parseDate( String date ) {
        try {
            return simpleDateFormat.parse( date );
        } catch ( ParseException e ) {
            throw new IllegalStateException( "не схавал дату, ищи косяк!" );
        }
    }

    public static Double parseValue( String value ) {
        return Double.parseDouble( value.replace( " ", "" ) );
    }
}
