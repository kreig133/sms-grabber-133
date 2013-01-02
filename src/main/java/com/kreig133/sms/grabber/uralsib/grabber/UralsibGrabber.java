package com.kreig133.sms.grabber.uralsib.grabber;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.kreig133.sms.grabber.Grabber;
import com.kreig133.sms.grabber.model.Operation;
import com.kreig133.sms.grabber.model.RawParseInfo;
import com.kreig133.sms.grabber.model.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kreig133
 * @version 1.0
 */
public class UralsibGrabber implements Grabber {

    @Override
    public Request parseSms(String smsText) {

        RawParseInfo infoFromSms = getInfoFromSms( smsText );

        return Request.Factory.build( infoFromSms );
    }

    static Table<Operation, String, Integer> indexTable = HashBasedTable.create();
    static Map<Operation, Pattern> patterns = new HashMap<Operation, Pattern>();

    static {
        registerInfo( Operation.CREDIT                   , Patterns.CREDIT_REGEX        , 2, 3, null, null, 1 );
        registerInfo( Operation.DEBIT                    , Patterns.DEBIT_REGEX         , 2, 3, null, null, 1 );
        registerInfo( Operation.REPLENISHMENT            , Patterns.REPLENISHMENT_REGEX , 3, null, null, 1, 2 );
        registerInfo( Operation.PAYMENT_OR_REDEMPTION_OF , Patterns.PAYMENT_REGEX       , 4, 2, 5, 1, 3       );
    }

     static void registerInfo(
            Operation operation,
            String pattern,
            Integer currencyIndex,
            Integer dateIndex,
            Integer payeeIndex,
            Integer cardNumberIndex,
            Integer valueIndex
    ) {
        patterns.put( operation, Pattern.compile( pattern ) );
        putToIndexTable( operation, RawParseInfo.CURRENCY, currencyIndex );
        putToIndexTable( operation, RawParseInfo.DATE, dateIndex );
        putToIndexTable( operation, RawParseInfo.PAYEE, payeeIndex );
        putToIndexTable( operation, RawParseInfo.CARD_NUMBER, cardNumberIndex );
        putToIndexTable( operation, RawParseInfo.VALUE, valueIndex );
    }

    private static void putToIndexTable( Operation operation, String field, Integer groupIndex ) {
        if( groupIndex != null ) {
            indexTable.put( operation, field, groupIndex );
        }
    }

    public static RawParseInfo getInfoFromSms( String smsText ) {
        smsText = smsText.replaceFirst( "^BANK\\sURALSIB\\.", "" );

        Matcher matcher = null;
        Operation currentOperation = null;

        for ( Operation operation : Operation.values() ) {
            Pattern pattern = patterns.get( operation );

            if( pattern == null ) continue;

            matcher = pattern.matcher( smsText );

            if ( matcher.matches() ) {
                currentOperation = operation;
                break;
            }
        }

        if ( currentOperation == null ) {
            throw new IllegalStateException();
        }

        RawParseInfo result = new RawParseInfo();

        result.currency   = indexTable.get( currentOperation, RawParseInfo.CURRENCY    ) == null ? null
                : matcher.group( indexTable.get( currentOperation, RawParseInfo.CURRENCY    ) );
        result.date       = indexTable.get( currentOperation, RawParseInfo.DATE        ) == null ? null
                : matcher.group( indexTable.get( currentOperation, RawParseInfo.DATE        ) );
        result.payee      = indexTable.get( currentOperation, RawParseInfo.PAYEE       ) == null ? null
                : matcher.group( indexTable.get( currentOperation, RawParseInfo.PAYEE       ) );
        result.cardNumber = indexTable.get( currentOperation, RawParseInfo.CARD_NUMBER ) == null ? null
                : matcher.group( indexTable.get( currentOperation, RawParseInfo.CARD_NUMBER ) );
        result.value      = indexTable.get( currentOperation, RawParseInfo.VALUE       ) == null ? null
                : matcher.group( indexTable.get( currentOperation, RawParseInfo.VALUE       ) );
        result.operation = currentOperation == Operation.PAYMENT_OR_REDEMPTION_OF
                ? ( isATM( result.payee ) ? Operation.REDEMPTION_OF : Operation.PAYMENT ) : currentOperation;

        return result;
    }

    static final Pattern ATM_MATCHER = Pattern.compile( "ATM-\\d{8}.*" );

    static boolean isATM( String payee ) {
        return ATM_MATCHER.matcher( payee ).matches();
    }

    static class Patterns {
        public static final String START = "(?x) \n ";

        public static final String SPACE = "\\s \n ";

        public static final String DEBIT = "SPISANIE\\sSREDSTV\\sSO\\sSCHETA";

        public static final String CREDIT = "POSTUPLENIE\\sSREDSTV\\sNA\\sSCHET";

        public static final String OTHER  = ".* \n ";

        public static final String CURRENCY_SUBPATTERN = "(RUR|USD|EUR)    # валюта платежа   \n ";

        public static final String DATETIME_SUBPATTERN = "(\\S+)\\s[\\d:]+ # время транзакции \n ";

        public static final String SUM_SUBPATTERN = "([\\d\\s]+\\.\\d\\d)       # сумма платежа    \n ";

        public static final String CARD_SUBPATTERN = "[\\d*]+(\\d{4})  # номер карты      \n ";

        public static final String PAYMENT_REGEX = START +
                CARD_SUBPATTERN + SPACE + DATETIME_SUBPATTERN +
                "     \\ssumma\\s                                         \n" +
                SUM_SUBPATTERN + SPACE + CURRENCY_SUBPATTERN +
                "     ;(.*?)\\sin\\s[^,;]+,\\w{3};      # получатель      \n" +
                OTHER;

        public static final String COMMON_PIECE_OF_CREDIT_AND_DEBIT = ": \n" +
                SPACE + SUM_SUBPATTERN +
                SPACE + CURRENCY_SUBPATTERN +
                SPACE + DATETIME_SUBPATTERN + OTHER;

        public static final String DEBIT_REGEX = START + DEBIT + COMMON_PIECE_OF_CREDIT_AND_DEBIT;

        public static final String CREDIT_REGEX = START + CREDIT + COMMON_PIECE_OF_CREDIT_AND_DEBIT;

        public static final String REPLENISHMENT_REGEX = START +
                "Popolnenie\\sraskhodnogo\\slimita\\scard\\s  \n" +
                CARD_SUBPATTERN + SPACE + SUM_SUBPATTERN + SPACE + CURRENCY_SUBPATTERN + OTHER;
    }

}
