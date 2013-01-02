package com.kreig133.sms.grabber.model.interpretator;

import com.kreig133.sms.grabber.model.RawParseInfo;
import com.kreig133.sms.grabber.model.Request;
import com.kreig133.sms.grabber.model.Transaction;
import com.kreig133.sms.grabber.model.catalog.AccountType;
import com.kreig133.sms.grabber.model.catalog.Accounts;
import com.kreig133.sms.grabber.model.catalog.Currency;
import com.kreig133.sms.grabber.model.catalog.ReminderCategory;

/**
 * @author kreig133
 * @version 1.0
 */
public class ReplenishmentRawParseInfoInterpretator extends AbstractRawParseInfoInterpretator {

    @Override
    public Request interpretate( RawParseInfo parseInfo ) {
        Transaction transaction = new Transaction();

        transaction.account_income  = Accounts.getAccount( Accounts.DEFAULT );
        transaction.account_outcome = Accounts.getAccount( Accounts.CASH    );
        transaction.income   = parseValue( parseInfo.value ); //TODO а если валюта?
        transaction.outcome  = parseValue( parseInfo.value ); //TODO а если валюта?
        transaction.category = ReminderCategory.TWO.getId(); //TODO WTF?

        Currency currency = Currency.valueOf( parseInfo.currency );
        transaction.instrument_income  = currency.getId();
        transaction.instrument_outcome = currency.getId();
        transaction.type_income        = AccountType.CCARD.getName(); //TODO
        transaction.type_outcome       = AccountType.CASH.getName();  //TODO
        transaction.direction          = 0; //TODO WTF?
        transaction.inbalance_income   = true;
        transaction.inbalance_outcome  = true;

        transaction.date = parseDate( parseInfo.date );

        return transaction;
    }


}
