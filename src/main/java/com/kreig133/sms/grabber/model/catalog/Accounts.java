package com.kreig133.sms.grabber.model.catalog;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kreig133
 * @version 1.0
 */
public class Accounts {

    static Map<String, Integer> map = new HashMap<String, Integer>();

    public static final String CASH = "CASH";
    public static final String DEFAULT = "DEFAULT";

    static {
        map.put( "6465", 303530 );
        map.put( DEFAULT, 303530 );
        map.put( CASH  , 303513 );
    }

    public static Integer getAccount( String key ) {
        return map.get( key );
    }
}
